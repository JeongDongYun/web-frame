package p1;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login/*")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		dao = new MemberDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);

	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action: " + action);
		if (action == null) {
			forwardReq(request, response, "/index.jsp");
		} else if (action.equals("/login.do")) {
			if (request.getMethod().equalsIgnoreCase("GET"))
				forwardReq(request, response, "/login.jsp");
			else {
				System.out.println("test startㅋㅋㅋ");
				forwardReq(request, response, processLogin(request, response));
				
			}
		}
	}

	private String processLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("이부분 실행");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("test : " + email);
		HashMap<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		if (email == null || email.isEmpty()) {
			errors.put("email", true);
		}
		if (password == null || password.isEmpty()) {
			errors.put("password", true);
		}
		if (!errors.isEmpty()) {
			return "/login.jsp";
		}
		MemberVO member = dao.getMemberByEmail(email);
		if (!password.equals(member.getPassword())) {
			errors.put("notMatch", true);
			return "/login.jsp";
		} else {
			return "/index.jsp";
		}
	}

	private void forwardReq(HttpServletRequest request, HttpServletResponse response, String nextPage)
			throws ServletException, IOException {
		if (nextPage != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
	}
}
