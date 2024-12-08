import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class UserThree implements ActionListener, Runnable {

    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;

    BufferedReader reader;
    BufferedWriter writer;
    String name = "sherin";

    int xMouse, yMouse; // Variables to store mouse position when dragging

    UserThree() {

        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 400, 70);
        p1.setLayout(null);
        f.add(p1);

        // Make the window moveable
        p1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Store the mouse position when pressed
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        p1.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Move the window when mouse is dragged
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                f.setLocation(x - xMouse, y - yMouse);
            }
        });

        // Back button icon
        ImageIcon i1 = new ImageIcon("E:\\oop allah vorsha\\chatting-application-master\\chatting-application-master\\Chatting Application\\src\\icons\\ppicon.png");
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        // Profile icon
        ImageIcon i4 = new ImageIcon("E:\\oop allah vorsha\\chatting-application-master\\chatting-application-master\\Group Chatting Application\\src\\icons\\mirzapur.png");
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);

        // Video call icon
        ImageIcon i7 = new ImageIcon("E:\\oop allah vorsha\\chatting-application-master\\chatting-application-master\\Chatting Application\\src\\icons\\video.png");
        Image i8 = i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(260, 20, 25, 25);
        p1.add(video);

        // Phone call icon
        ImageIcon i10 = new ImageIcon("E:\\oop allah vorsha\\chatting-application-master\\chatting-application-master\\Chatting Application\\src\\icons\\phone.png");
        Image i11 = i10.getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(320, 20, 30, 25);
        p1.add(phone);

        // More options icon
        ImageIcon i13 = new ImageIcon("E:\\oop allah vorsha\\chatting-application-master\\chatting-application-master\\Chatting Application\\src\\icons\\3icon.png");
        Image i14 = i13.getImage().getScaledInstance(10, 20, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(370, 20, 10, 20);
        p1.add(morevert);

        JLabel name = new JLabel("hallbasinda");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("rahad, ovi, sherin, nibir, dipti");
        status.setBounds(110, 35, 160, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        a1 = new JPanel();
        a1.setBounds(5, 75, 390, 480);
        a1.setBackground(Color.WHITE);
        f.add(a1);

        text = new JTextField();
        text.setBounds(5, 565, 270, 30);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(280, 565, 105, 30);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        f.add(send);

        f.setSize(400, 600); // Updated frame size
        f.setLocation(100, 100);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);

        try {
            Socket socket = new Socket("localhost", 2003);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = "<html><p>" + name + "</p><p>" + text.getText() + "</p></html>";

            JPanel p2 = formatLabel(out);

            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(Color.WHITE);
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            writer.write(out);
            writer.write("\r\n");
            writer.flush();

            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(0, 15, 0, 50));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    public void run() {
        try {
            String msg = "";
            while (true) {
                msg = reader.readLine();
                if (msg.contains(name)) {
                    continue;
                }

                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.setBackground(Color.WHITE);
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);

                a1.add(vertical, BorderLayout.PAGE_START);

                f.repaint();
                f.invalidate();
                f.validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserThree two = new UserThree();
        Thread t1 = new Thread(two);
        t1.start();
    }
}
