import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GadgetShop extends JFrame implements ActionListener {

    private ArrayList<Gadget> gadgets;

    // 10 text fields
    private JTextField txtModel, txtPrice, txtWeight, txtSize;
    private JTextField txtCredit, txtMemory;
    private JTextField txtPhoneNumber, txtDuration, txtDownloadSize;
    private JTextField txtDisplayNumber;

    // Buttons
    private JButton btnAddMobile, btnAddMP3, btnClear, btnDisplayAll, btnMakeCall, btnDownloadMusic;

    public GadgetShop() {
        super("Gadget Shop");
        gadgets = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 260);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridBagLayout());
        add(panel, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Row 0 labels
        c.gridy = 0;
        addLabel(panel, "Model:", 0, c);
        addLabel(panel, "Price:", 2, c);
        addLabel(panel, "Weight:", 4, c);
        addLabel(panel, "Size:", 6, c);

        // Row 1 fields
        c.gridy = 1;
        txtModel = addText(panel, 0, c);
        txtPrice = addText(panel, 2, c);
        txtWeight = addText(panel, 4, c);
        txtSize = addText(panel, 6, c);

        // Row 2 labels
        c.gridy = 2;
        addLabel(panel, "Credit:", 0, c);
        addLabel(panel, "Memory:", 2, c);

        // Row 3 fields + add buttons
        c.gridy = 3;
        txtCredit = addText(panel, 0, c);
        txtMemory = addText(panel, 2, c);

        btnAddMobile = addButton(panel, "Add Mobile", 4, c);
        btnAddMP3 = addButton(panel, "Add MP3", 6, c);

        // Row 4 labels
        c.gridy = 4;
        addLabel(panel, "Phone No:", 0, c);
        addLabel(panel, "Duration:", 2, c);
        addLabel(panel, "Download:", 4, c);
        addLabel(panel, "Display Number:", 6, c);

        // Row 5 fields
        c.gridy = 5;
        txtPhoneNumber = addText(panel, 0, c);
        txtDuration = addText(panel, 2, c);
        txtDownloadSize = addText(panel, 4, c);
        txtDisplayNumber = addText(panel, 6, c);

        // Row 6 buttons
        c.gridy = 6;
        btnMakeCall = addButton(panel, "Make A Call", 0, c);
        btnDownloadMusic = addButton(panel, "Download Music", 2, c);
        btnClear = addButton(panel, "Clear", 4, c);
        btnDisplayAll = addButton(panel, "Display All", 6, c);

        setVisible(true);
    }

    private void addLabel(JPanel panel, String text, int x, GridBagConstraints c) {
        c.gridx = x;
        c.gridwidth = 2;
        panel.add(new JLabel(text), c);
    }

    private JTextField addText(JPanel panel, int x, GridBagConstraints c) {
        c.gridx = x;
        c.gridwidth = 2;
        JTextField t = new JTextField(12);
        panel.add(t, c);
        return t;
    }

    private JButton addButton(JPanel panel, String text, int x, GridBagConstraints c) {
        c.gridx = x;
        c.gridwidth = 2;
        JButton b = new JButton(text);
        b.addActionListener(this);
        panel.add(b, c);
        return b;
    }

    // Input methods
    public String getModelFromGUI() { return txtModel.getText().trim(); }
    public String getSizeFromGUI() { return txtSize.getText().trim(); }
    public String getPhoneNumberFromGUI() { return txtPhoneNumber.getText().trim(); }

    public double getPriceFromGUI() { return Double.parseDouble(txtPrice.getText().trim()); }
    public int getWeightFromGUI() { return Integer.parseInt(txtWeight.getText().trim()); }
    public int getCreditFromGUI() { return Integer.parseInt(txtCredit.getText().trim()); }
    public int getMemoryFromGUI() { return Integer.parseInt(txtMemory.getText().trim()); }
    public int getDurationFromGUI() { return Integer.parseInt(txtDuration.getText().trim()); }
    public int getDownloadSizeFromGUI() { return Integer.parseInt(txtDownloadSize.getText().trim()); }

    // Display number with try/catch + range check
    public int getDisplayNumberFromGUI() {
        int displayNumber = -1;

        try {
            displayNumber = Integer.parseInt(txtDisplayNumber.getText().trim());

            if (displayNumber < 0 || displayNumber >= gadgets.size()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Display number out of range.\nValid range: 0 to " + (gadgets.size() - 1),
                        "Range Error",
                        JOptionPane.ERROR_MESSAGE
                );
                displayNumber = -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Display number must be an integer.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            displayNumber = -1;
        }

        return displayNumber;
    }

    // Button handler
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnAddMobile) {
            handleAddMobile();
        } else if (src == btnAddMP3) {
            handleAddMP3();
        } else if (src == btnClear) {
            handleClear();
        } else if (src == btnDisplayAll) {
            handleDisplayAll();
        } else if (src == btnMakeCall) {
            handleMakeCall();
        } else if (src == btnDownloadMusic) {
            handleDownloadMusic();
        }
    }

    private void handleAddMobile() {
        try {
            Mobile m = new Mobile(
                    getModelFromGUI(),
                    getPriceFromGUI(),
                    getWeightFromGUI(),
                    getSizeFromGUI(),
                    getCreditFromGUI()
            );
            gadgets.add(m);
            JOptionPane.showMessageDialog(this, "Mobile added. Index = " + (gadgets.size() - 1));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number entered (price/weight/credit).", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAddMP3() {
        try {
            MP3 mp3 = new MP3(
                    getModelFromGUI(),
                    getPriceFromGUI(),
                    getWeightFromGUI(),
                    getSizeFromGUI(),
                    getMemoryFromGUI()
            );
            gadgets.add(mp3);
            JOptionPane.showMessageDialog(this, "MP3 added. Index = " + (gadgets.size() - 1));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number entered (price/weight/memory).", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleClear() {
        txtModel.setText("");
        txtPrice.setText("");
        txtWeight.setText("");
        txtSize.setText("");
        txtCredit.setText("");
        txtMemory.setText("");
        txtPhoneNumber.setText("");
        txtDuration.setText("");
        txtDownloadSize.setText("");
        txtDisplayNumber.setText("");
    }

    private void handleDisplayAll() {
        System.out.println("----- DISPLAY ALL GADGETS -----");
        for (int i = 0; i < gadgets.size(); i++) {
            System.out.println("Display number: " + i);
            gadgets.get(i).display();
            System.out.println("------------------------------");
        }
        JOptionPane.showMessageDialog(this, "Displayed all gadgets in the console.");
    }

    private void handleMakeCall() {
        int index = getDisplayNumberFromGUI();
        if (index == -1) return;

        try {
            String phone = getPhoneNumberFromGUI();
            int duration = getDurationFromGUI();

            Gadget g = gadgets.get(index);
            if (g instanceof Mobile) {
                ((Mobile) g).makeCall(phone, duration);
                JOptionPane.showMessageDialog(this, "Call attempted. Check console output.");
            } else {
                JOptionPane.showMessageDialog(this, "That gadget is not a Mobile.", "Type Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duration must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDownloadMusic() {
        int index = getDisplayNumberFromGUI();
        if (index == -1) return;

        try {
            int size = getDownloadSizeFromGUI();

            Gadget g = gadgets.get(index);
            if (g instanceof MP3) {
                ((MP3) g).downloadMusic(size);
                JOptionPane.showMessageDialog(this, "Download attempted. Check console output.");
            } else {
                JOptionPane.showMessageDialog(this, "That gadget is not an MP3.", "Type Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Download size must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method (needed)
    public static void main(String[] args) {
        new GadgetShop();
    }
}