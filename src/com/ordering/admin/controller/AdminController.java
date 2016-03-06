package com.ordering.admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ordering.admin.po.Admin;
import com.ordering.admin.service.AdminService;
import com.ordering.seller.po.Seller;
import com.ordering.seller.service.SellerService;
import com.ordering.utils.PageBean;

import cn.dsna.util.images.ValidateCode;

@Controller
public class AdminController {
	
	@Resource(name="adminService")
	private AdminService adminService;
	@Resource(name="sellerService")
	private SellerService sellerService;
	@Resource(name="pooledConnectionFactory")
	private PooledConnectionFactory factory;
	@Resource(name="queueDestination")
	private ActiveMQQueue queue;
	
	@RequestMapping(value="/verifyCode")
	public String VerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ValidateCode vc = new ValidateCode(100,45,4,20);
		String code = vc.getCode();
		System.out.println(code);
		HttpSession session = request.getSession();
		session.setAttribute("verifyCode", code);
		BufferedImage image = vc.getBuffImg();
		ImageIO.write(image, "bmp", response.getOutputStream());
		return null;
	}
	
	@RequestMapping(value="/login")
	public String Login(HttpServletRequest request,HttpServletResponse response,Model model) {
		HttpSession session = (HttpSession) request.getSession();
		if(session.getAttribute("admin")!=null) {
			return "main";
		}
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String verfiyCode = request.getParameter("verifyCode");
		String _verfiyCode = (String)session.getAttribute("verifyCode");
		
		Admin admin = new Admin();
		admin.setAemail(email);
		admin.setApassword(password);
		if(verfiyCode.equalsIgnoreCase(_verfiyCode)) {
			Admin adminfind = adminService.AdminLogin(admin);
			System.out.println(adminfind);
			if(adminfind!=null) {
				model.addAttribute("admin", admin);
				session.setAttribute("admin", admin);
				return "/main";
			} else {
				model.addAttribute("msg","登录失败,请联系数据库管理员");
				return "/login";
			}
		} else {
			model.addAttribute("msg","验证码错误");
			return "/login";
		}
	}
	
	@RequestMapping(value="/refershQueue")
	public String refershQueue(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		
		/**
		 * 1、查看队列中是否存在数据
		 * 2、如果有数据就添加在库中
		 * 3、显示所有的数据
		 */
		new JSONObject();
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		MessageConsumer consumer = session.createConsumer(queue);
		TextMessage message = (TextMessage) consumer.receive();
		
		String str_seller = message.getText();
		
		JSONObject obj = JSONObject.fromObject(str_seller);//将json字符串转换为json对象
		Seller seller = (Seller)JSONObject.toBean(obj,Seller.class);//将建json对象转换为Seller对象
		
		String path = "http://127.0.0.1:8080/OnlineSeller/";
		seller.setVerfiyCard(path+seller.getVerfiyCard());
		seller.setSalefile(path+seller.getSalefile());
		seller.setSafefile(path+seller.getSafefile());
		seller.setEmailActive("1");
		sellerService.registSeller(seller);
		
		consumer.close();
		session.close();
		connection.close();
		
		return "queueFresh";
	}
	
	@RequestMapping(value="/showAllSeller")
	public String showAllSeller(HttpServletRequest request,HttpServletResponse response,Model model) {
		
		/**
		 * 1、获取页面传递的PageCurrent
		 * 2、给定PageSize的值
		 * 3、使用PageCurrent和PageSize调用Service方法，得到PageBean，保存request域
		 * 4、转发到/admin/dept_info.jsp
		 */
		int PageCurrent = getPageCurrent(request);//获取当前页
		int PageSize = 4;//获取每页的记录数
		PageBean<Seller> pageBean = sellerService.queryAll_PageSeller(PageCurrent, PageSize);
		pageBean.setUrl(getUrl(request));
		request.setAttribute("pb", pageBean);
		return "showSellerAll";
	}
	
	/**
	 * 获取当前的页面值
	 * @param request
	 * @return
	 */
	private int getPageCurrent(HttpServletRequest request){
		String value = request.getParameter("PageCurrent");
		if(value == null || value.trim().isEmpty()){
			return 1;
		} else{
			return Integer.parseInt(value);
		}
	}
	/**
	 * 截取url
	 * @param request
	 * @return
	 */
	public String getUrl(HttpServletRequest request){
		String contextPath = request.getContextPath();//获取项目名
		String servletPath = request.getServletPath();//获取servletPath，即/CustomerServlet
		String queryString = request.getQueryString();//获取问号之后的参数部份
		if(queryString == null) {
			return contextPath + servletPath + "?" + queryString;
		}
		//  判断参数部份中是否包含pc这个参数，如果包含，需要截取下去，不要这一部份。
		if(queryString.contains("&PageCurrent=")) {
			int index = queryString.lastIndexOf("&PageCurrent=");
			queryString = queryString.substring(0, index);
		}
		
		return contextPath + servletPath + "?" + queryString;
	}
	
	@RequestMapping(value="/preAccpetSeller")
	public String preAccpetSeller(HttpServletRequest request) {
		String sid = request.getParameter("sid");
		System.out.println("Contronller====sid===="+sid);
		Seller seller = sellerService.findSellerBySid(sid);
		System.out.println("Contronller====seller===="+seller);
		request.setAttribute("seller", seller);
		return "form";
	}
	
	@RequestMapping(value="/passSeller")
	public String passSeller(HttpServletRequest request, HttpServletResponse response, Model model) 
		throws Exception{
		String sid = request.getParameter("sid");
		sellerService.passAccpet(sid,true);
		
		Connection connection = factory.createConnection();
		connection.start();
		
		javax.jms.Session sessionJMS = connection.createSession(true, javax.jms.Session.CLIENT_ACKNOWLEDGE);
		MessageProducer producer = sessionJMS.createProducer(queue);
		TextMessage message = sessionJMS.createTextMessage();
		
		message.setText(sid + "-" + true);
		producer.send(message);
		
		sessionJMS.commit();
		producer.close();
		sessionJMS.close();
		connection.close();
		
		return showAllSeller(request,response,model);
	}
	
	@RequestMapping(value="/cancleSeller")
	public String cancleSeller(HttpServletRequest request, HttpServletResponse response, Model model) {
		String sid = request.getParameter("sid");
		sellerService.passAccpet(sid,false);
		return showAllSeller(request,response,model);
	}
}
