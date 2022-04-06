import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GUI extends JFrame implements ItemListener {

    private final JPanel contentPane;
    private final JTextField textField;
    private final JLabel PasswordLengthLabel;
    private final JCheckBox[] arr = new JCheckBox[3];

    // Images
    private final JLabel numberimg;
    private final JLabel symbolimg;

    private final JLabel ClipBoardLabel;

    /**
     * Create the frame.
     */
    public GUI() {
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(GUI.class.getResource("close17x17.png")));
        setUndecorated(true);
        setBounds(new Rectangle(0, 0, 513, 327));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 513, 327);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //THIS IS GOOD
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 513, 327);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton disposeButton = new JButton("");
        disposeButton.setContentAreaFilled(false);
        disposeButton.setBorderPainted(false);
        disposeButton.setFocusPainted(false);
        disposeButton.setIcon(
                new ImageIcon(GUI.class.getResource("close17x17.png")));
        disposeButton.setBounds(445, 20, 22, 17);
        disposeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
                dispose();
            }
        });

        JButton ClipBoardButton = new JButton("");
        ClipBoardButton.setOpaque(true);
        ClipBoardButton.setFocusPainted(false);
        ClipBoardButton.setContentAreaFilled(false);
        ClipBoardButton.setBorderPainted(false);
        ClipBoardButton.setIcon(new ImageIcon(GUI.class.getResource("clipboardSmall.png")));
        ClipBoardButton.setBounds(450, 260, 17, 17);
        ClipBoardButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String myString = textField.getText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

                new makeClipBoardTextVisible().execute();
            }

        });

        ClipBoardLabel = new JLabel("Password coppied to clipboard");
        ClipBoardLabel.setForeground(Color.BLACK);
        ClipBoardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ClipBoardLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        ClipBoardLabel.setBounds(87, 286, 336, 16);
        panel.add(ClipBoardLabel);
        panel.add(ClipBoardButton);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setOpaque(false);
        textField.setEnabled(false);
        textField.setBorder(null);

        textField.setBounds(97, 257, 329, 26);
        panel.add(textField);
        textField.setColumns(10);
        panel.add(disposeButton);

        JSlider slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setOpaque(false);
        slider.setFocusable(false);
        slider.setBorder(null);
        slider.setBackground(Color.WHITE);
        slider.setForeground(Color.BLACK);
        slider.setValue(0);
        slider.setMinimum(5);
        slider.setMaximum(40);
        slider.setBounds(220, 149, 190, 29);
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                PasswordLengthLabel.setText(String.format("Password Length: %d", slider.getValue()));
            }

        });
        panel.add(slider);

        PasswordLengthLabel = new JLabel("Password Length: 5");
        PasswordLengthLabel.setForeground(Color.WHITE);
        PasswordLengthLabel.setBounds(116, 151, 168, 16);
        panel.add(PasswordLengthLabel);

        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(GUI.class.getResource("Rectangle 4@3x.png")));
        lblNewLabel_1.setBounds(74, 257, 368, 26);
        panel.add(lblNewLabel_1);

        JButton GenerateButton = new JButton("");
        GenerateButton.setContentAreaFilled(false);
        GenerateButton.setBorderPainted(false);
        GenerateButton.setFocusPainted(false);
        GenerateButton.setIcon(new ImageIcon(GUI.class.getResource("Button@3x.png")));
        GenerateButton.setBounds(165, 187, 164, 53);
        GenerateButton.addActionListener(new ActionListener() {
            // Anything with implemnts you can do this in - inner clas in a merhod
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String event = "";
                if (arr[0].isSelected()) {
                    event = "Simple";
                } else if (arr[1].isSelected()) {
                    event = "Strong";
                } else {
                    event = "Very Strong";
                }

                String pass = null;
                pass = getPassword(slider.getValue(), event);
                textField.setText(pass);
            }

        });
        panel.add(GenerateButton);

        JLabel Lowercaseimg = new JLabel("");
        Lowercaseimg
                .setIcon(new ImageIcon(GUI.class.getResource("tick.png")));
        Lowercaseimg.setBounds(145, 98, 17, 17);
        panel.add(Lowercaseimg);

        JLabel Uppercase_1_1_1 = new JLabel("Symbols");
        Uppercase_1_1_1.setForeground(Color.WHITE);
        Uppercase_1_1_1.setBounds(413, 96, 81, 16);
        panel.add(Uppercase_1_1_1);

        symbolimg = new JLabel("");
        symbolimg.setIcon(
                new ImageIcon(GUI.class.getResource("no-entry.png")));
        symbolimg.setBounds(393, 97, 17, 17);
        panel.add(symbolimg);

        JLabel Uppercase_1_1 = new JLabel("Number");
        Uppercase_1_1.setForeground(Color.WHITE);
        Uppercase_1_1.setBounds(294, 97, 81, 16);
        panel.add(Uppercase_1_1);

        numberimg = new JLabel("");
        numberimg.setIcon(
                new ImageIcon(GUI.class.getResource("no-entry.png")));
        numberimg.setBounds(274, 98, 17, 17);
        panel.add(numberimg);

        JLabel lblLowercase = new JLabel("Lowercase");
        lblLowercase.setForeground(Color.WHITE);
        lblLowercase.setBounds(165, 97, 81, 16);
        panel.add(lblLowercase);

        JLabel Uppercase = new JLabel("Uppercase");
        Uppercase.setForeground(Color.WHITE);
        Uppercase.setBounds(47, 97, 81, 16);
        panel.add(Uppercase);

        JLabel UpperCaseimg = new JLabel("");
        UpperCaseimg
                .setIcon(new ImageIcon(GUI.class.getResource("tick.png")));
        UpperCaseimg.setBounds(27, 98, 17, 17);
        panel.add(UpperCaseimg);

        JCheckBox Strong = new JCheckBox("Strong");
        Strong.setFocusPainted(false);
        Strong.setContentAreaFilled(false);
        Strong.setForeground(Color.WHITE);
        Strong.setBounds(203, 61, 81, 23);
        Strong.addItemListener(this);
        panel.add(Strong);

        JCheckBox VStrong = new JCheckBox("Very Strong");
        VStrong.setFocusPainted(false);
        VStrong.setContentAreaFilled(false);
        VStrong.setForeground(Color.WHITE);
        VStrong.setBounds(383, 61, 104, 23);
        VStrong.addItemListener(this);
        panel.add(VStrong);

        JCheckBox Simple = new JCheckBox("Simple");
        Simple.setFocusPainted(false);
        Simple.setContentAreaFilled(false);
        Simple.setBorder(null);
        Simple.setSelected(true);
        Simple.setForeground(Color.WHITE);
        Simple.setBounds(27, 61, 81, 23);
        Simple.addItemListener(this);
//		Simple.addItemListener(new ItemListener() {
//
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getStateChange() == ItemEvent.SELECTED) {
//				arr[1].setSelected(false);
//				arr[2].setSelected(false);
//				}else if (e.getStateChange() == ItemEvent.DESELECTED) {
//					if (arr[1].isSelected() && arr[2].isSelected()) {
//						arr[0].setSelected(false);
//					}
//				}
//
//
//			}
//
//		});
        panel.add(Simple);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(GUI.class.getResource("Component 1 \u2013 1@3x.png")));
        lblNewLabel.setBounds(0, 0, 513, 327);
        panel.add(lblNewLabel);
        panel.setBackground(new Color(0, 0, 0, 0));

        arr[0] = Simple;
        arr[1] = Strong;
        arr[2] = VStrong;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                    frame.setBackground(new Color(0, 0, 0, 0));

                    // This or
                    frame.setLocationRelativeTo(null);

                    //
//					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//					this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String getPassword(int n, String s) {
        String pass = "";

        char[] arr = null;
        if (s.equals("Simple")) {
            arr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        } else if (s.equals("Strong")) {
            arr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        } else {
            arr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()-_=+[{}]\\|<,.>/?"
                    .toCharArray();
        }

        for (int i = 0; i < n; i++) {
            int ran = (int) (Math.random() * arr.length);

            pass += String.valueOf(arr[ran]);
        }

        return pass;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JCheckBox selected = (JCheckBox) e.getSource();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != selected) {
                    arr[i].setSelected(false);
                }
            }

            if (selected == arr[0]) {
                // simple
                numberimg.setIcon(new ImageIcon(
                        GUI.class.getResource("no-entry.png")));
                symbolimg.setIcon(new ImageIcon(
                        GUI.class.getResource("no-entry.png")));
            } else if (selected == arr[1]) {
                // Strong

                numberimg.setIcon(
                        new ImageIcon(GUI.class.getResource("tick.png")));
                symbolimg.setIcon(new ImageIcon(
                        GUI.class.getResource("no-entry.png")));

            } else {
                // vStrong
                numberimg.setIcon(
                        new ImageIcon(GUI.class.getResource("tick.png")));
                symbolimg.setIcon(
                        new ImageIcon(GUI.class.getResource("tick.png")));
            }
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            JCheckBox deselected = (JCheckBox) e.getSource();
            boolean otherSelected = false;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != deselected)
                    if (arr[i].isSelected()) {
                        otherSelected = true;
                        break;
                    }

            }
            if (!otherSelected) {
                deselected.setSelected(true);
            }
        }
    }

    class passwordCreation extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            // TODO Auto-generated method stub
            return null;
        }

    }

    class makeClipBoardTextVisible extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {
            // TODO Auto-generated method stub
            ClipBoardLabel.setForeground(Color.CYAN);
            Thread.sleep(3000);
            return null;
        }

        protected void done() {
            ClipBoardLabel.setForeground(Color.BLACK);
        }
    }


}
