package com.javapapers.java.gcm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@WebServlet("/GCMNotification")
public class GCMNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Put your Google API Server Key here
	private static final String GOOGLE_SERVER_KEY = "AIzaSyDA5dlLInMWVsJEUTIHV0u7maB82MCsZbU";
	static final String MESSAGE_KEY = "message";	
	java.util.Date d=new java.util.Date();
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
	public GCMNotification() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		Result result = null;

		String share = request.getParameter("shareRegId");

		// GCM RedgId of Android device to send push notification
		String regId = "";
		if (share != null && !share.isEmpty()) {
			regId = request.getParameter("regId");
			PrintWriter writer = new PrintWriter("GCMRegId.txt");
			writer.println(regId);
			writer.close();
			request.setAttribute("pushStatus", "GCM RegId Received.");
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} else {

			try {
			      File file = new File("E:\\LogFiles\\GCMRegId_"+dateFormat1.format(d)+".txt"); 
			      if (file.createNewFile()){
			      }
			      FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
				BufferedReader br = new BufferedReader(new FileReader(
						"GCMRegId.txt"));
				regId = br.readLine();
				br.close();
				String userMessage = request.getParameter("message");
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
				Message message = new Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage).build();
				System.out.println("regId: " + regId);
				result = sender.send(message, regId, 1);
				request.setAttribute("pushStatus", result.toString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				request.setAttribute("pushStatus",
						"RegId required: " + ioe.toString());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		}
	}
}
