import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Frame implements Runnable,ActionListener  {
		Button b;
		TextField tf;
		TextArea ta;
		Socket s;
		BufferedReader bufferedReader;
		PrintWriter pw;
		Thread th;
		public Client() {
			Frame f = new Frame("Client Side Chatting");
			f.setLayout(new FlowLayout());
			f.setBackground(Color.DARK_GRAY);
			b = new Button("Send");
			b.addActionListener(this);
			f.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent arg0) {
					f.dispose();
				}
			});
			tf = new TextField(15);
			ta = new TextArea(12,20);
			ta.setBackground(Color.ORANGE);
			f.add(tf);
			f.add(b);
			f.add(ta);
			try {
			s = new Socket(InetAddress.getLocalHost(), 5000);
			bufferedReader=new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream(),true);
			}
			catch (Exception e) {}
			th = new Thread(this);
			th.setDaemon(true);
			th.start();
			setFont(new Font("Arial",Font.BOLD,20));
			f.setSize(200,200);
			f.setVisible(true);
			f.setLocation(100,300);
			f.validate();
			}
		public void actionPerformed(ActionEvent ae) {
			pw.println(tf.getText());
			tf.setText("");
			}
		@Override
		public void run() {
		while(true) 
		{
			try {
				ta.append(bufferedReader.readLine()+"/n");
				
			}catch (Exception e) {}
		}
		
		}
		public static void main(String[] args) {
			Client client = new Client();
		}
		
		
}
