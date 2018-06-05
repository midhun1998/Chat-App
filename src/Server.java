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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class Server extends Frame implements Runnable,ActionListener  {
	Button b;
	ServerSocket ss;
	PrintWriter pw;
	BufferedReader bufferedReader;
	Socket s;
	TextField tf;
	TextArea ta;
public Server() {
	Frame f = new Frame("Server Side Chatting");
	f.setLayout(new FlowLayout());
	f.setBackground(Color.MAGENTA);
	b = new Button("Send");
	b.setBackground(Color.YELLOW);
	b.addActionListener(this);
	tf = new TextField(15);
	ta = new TextArea(12,20);
	ta.setBackground(Color.WHITE);
	f.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent arg0) {
		f.dispose();
		}
	});
	f.add(tf);
	f.add(b);
	f.add(ta);
	try {
		 ss = new ServerSocket(5000);
		s= ss.accept();
		System.out.println(s);
		 bufferedReader=new BufferedReader(new InputStreamReader(s.getInputStream()));
		 pw  = new PrintWriter(s.getOutputStream(),true);
	}catch (Exception e) {}
		Thread th = new Thread();
		th.setDaemon(true);
		th.start();
		setFont(new Font("Arial",Font.BOLD,20));
		f.setSize(200,200);
		f.setLocation(300,300);
		f.setVisible(true);
		f.validate();
	}
@Override
public void actionPerformed(ActionEvent arg0) {
	pw.println(tf.getText());
	tf.setText("");
}

	@Override
	public void run() {
	while(true) {
		try {
		String s = bufferedReader.readLine();
		ta.append(s+"\n");
		}catch (Exception e) {}
	}
	}
	public static void main(String[] args) {
		Server server = new Server();
	}
	
}
