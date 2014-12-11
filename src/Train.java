
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.AbstractAction;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**@author Dean Mauro*/

public class Train extends javax.swing.JPanel {
    static DecimalFormat d1 = new DecimalFormat("#.#");
    static DecimalFormat d0 = new DecimalFormat("#");

    public Train(int numTrain) {
        initComponents();
        labelTrainNum.setText("Train "+numTrain);
        this.buttonSend.setActionCommand(String.valueOf(numTrain));  //Set Action Command to be the
                                                                     //train ID
        addListeners(this);
    }

    private void addListeners(Train t){
    // <editor-fold defaultstate="collapsed" desc="Add Listeners">
        final Train tr = t;
    //Enable the Reroute Button
        t.textRoute.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!tr.textRoute.getText().equals(""))
                    tr.buttonReroute.setEnabled(true);
                else
                    tr.buttonReroute.setEnabled(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!tr.textRoute.getText().equals(""))
                    tr.buttonReroute.setEnabled(true);
                else
                    tr.buttonReroute.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(!tr.textRoute.getText().equals(""))
                    tr.buttonReroute.setEnabled(true);
                else
                    tr.buttonReroute.setEnabled(false);
            }

          });
        
    
    //Enable the Send Button    
        t.textSetSpeed.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!tr.textSetSpeed.getText().equals("") && !tr.textSetAuthority.getText().equals(""))
                    tr.buttonSend.setEnabled(true);
                else
                    tr.buttonSend.setEnabled(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!tr.textSetSpeed.getText().equals("") && !tr.textSetAuthority.getText().equals(""))
                    tr.buttonSend.setEnabled(true);
                else
                    tr.buttonSend.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(!tr.textSetSpeed.getText().equals("") && !tr.textSetAuthority.getText().equals(""))
                    tr.buttonSend.setEnabled(true);
                else
                    tr.buttonSend.setEnabled(false);
            }

          });

        
    //Enable the Send Button     
        t.textSetAuthority.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!tr.textSetSpeed.getText().equals("") && !tr.textSetAuthority.getText().equals(""))
                    tr.buttonSend.setEnabled(true);
                else
                    tr.buttonSend.setEnabled(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!tr.textSetSpeed.getText().equals("") && !tr.textSetAuthority.getText().equals(""))
                    tr.buttonSend.setEnabled(true);
                else
                    tr.buttonSend.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(!tr.textSetSpeed.getText().equals("") && !tr.textSetAuthority.getText().equals(""))
                    tr.buttonSend.setEnabled(true);
                else
                    tr.buttonSend.setEnabled(false);
            }

          });
    }// </editor-fold>
    
    
    public static void setSpeed(Train t, double speed){
    // <editor-fold defaultstate="collapsed" desc="Set Speed">
        t.textSpeed.setText(d1.format(speed * 2.23694) + " mph");
    }// </editor-fold> 
    
    public static void setAuthority(Train t, double auth){
    // <editor-fold defaultstate="collapsed" desc="Set Authority">
        t.textAuthority.setText(d0.format(auth * 3.28084) + " ft.");
    }// </editor-fold> 
    
    public static void setPosition(Train t, double pos){
    // <editor-fold defaultstate="collapsed" desc="Set Position">
        t.textPosition.setText(d1.format(pos * 3.28084) + " ft.");
    }// </editor-fold> 
    
    public static double getSpeed(Train t){
    // <editor-fold defaultstate="collapsed" desc="Get Speed">
        String[] speed;
        speed = t.textSpeed.getText().split(" ", 2);
        
        return 0.44704 * Double.parseDouble(speed[0]);  //Send m/s
    }
    // </editor-fold> 
    
    public static double getAuthority(Train t){
    // <editor-fold defaultstate="collapsed" desc="Get Authority">
        String[] authority;
        authority = t.textAuthority.getText().split(" ", 2);
        
        return 0.3048 * Double.parseDouble(authority[0]);  //Send meters
    }// </editor-fold> 
    
    public static double getPosition(Train t){
    // <editor-fold defaultstate="collapsed" desc="Get Position">
        String[] position;
        position = t.textPosition.getText().split(" ", 2);
        
        return Double.parseDouble(position[0]);
    }// </editor-fold> 
    
        
        
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPane = new javax.swing.JLayeredPane();
        backPanel = new javax.swing.JPanel();
        Background = new javax.swing.JLabel();
        frontPanel = new javax.swing.JPanel();
        labelTrainNum = new javax.swing.JLabel();
        labelSpeed = new javax.swing.JLabel();
        labelAuthority = new javax.swing.JLabel();
        labelPosition = new javax.swing.JLabel();
        labelSetSpeed = new javax.swing.JLabel();
        labelSetAuthority = new javax.swing.JLabel();
        labelRoute = new javax.swing.JLabel();
        textSpeed = new javax.swing.JTextField();
        textAuthority = new javax.swing.JTextField();
        textPosition = new javax.swing.JTextField();
        textSetSpeed = new javax.swing.JTextField();
        textSetAuthority = new javax.swing.JTextField();
        buttonSend = new javax.swing.JButton(new AbstractAction("Send") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                //observer.SuggestedSpeed.setText(textSetSpeed.getText());
                //observer.SuggestedAuthority.setText(textSetAuthority.getText());
            }
        });
        textRoute = new javax.swing.JTextField();
        buttonReroute = new javax.swing.JButton(new AbstractAction("Reroute") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                //observer.NewRoute.setText(textRoute.getText());
            }
        });
        separator = new javax.swing.JSeparator();

        setOpaque(false);

        backPanel.setOpaque(false);

        Background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/model.png"))); // NOI18N

        javax.swing.GroupLayout backPanelLayout = new javax.swing.GroupLayout(backPanel);
        backPanel.setLayout(backPanelLayout);
        backPanelLayout.setHorizontalGroup(
            backPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backPanelLayout.createSequentialGroup()
                .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        backPanelLayout.setVerticalGroup(
            backPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );

        frontPanel.setOpaque(false);

        labelTrainNum.setFont(new java.awt.Font("Stencil", 0, 14)); // NOI18N
        labelTrainNum.setForeground(new java.awt.Color(255, 255, 255));
        labelTrainNum.setText("Train 1");

        labelSpeed.setText("Speed:");

        labelAuthority.setText("Authority:");

        labelPosition.setText("Position:");

        labelSetSpeed.setText("Set Speed:");

        labelSetAuthority.setText("Set Auth.:");

        labelRoute.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRoute.setText("Route:");

        textSpeed.setEditable(false);
        textSpeed.setBackground(new java.awt.Color(204, 255, 204));

        textAuthority.setEditable(false);
        textAuthority.setBackground(new java.awt.Color(204, 255, 204));

        textPosition.setEditable(false);
        textPosition.setBackground(new java.awt.Color(204, 255, 204));

        textSetSpeed.setBackground(new java.awt.Color(204, 255, 204));

        textSetAuthority.setBackground(new java.awt.Color(204, 255, 204));

        buttonSend.setText("Send");
        buttonSend.setEnabled(false);

        textRoute.setBackground(new java.awt.Color(204, 255, 204));

        buttonReroute.setText("Reroute");
        buttonReroute.setEnabled(false);

        javax.swing.GroupLayout frontPanelLayout = new javax.swing.GroupLayout(frontPanel);
        frontPanel.setLayout(frontPanelLayout);
        frontPanelLayout.setHorizontalGroup(
            frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frontPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonSend)
                .addGap(34, 34, 34))
            .addGroup(frontPanelLayout.createSequentialGroup()
                .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(frontPanelLayout.createSequentialGroup()
                            .addComponent(labelRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(textRoute))
                        .addGroup(frontPanelLayout.createSequentialGroup()
                            .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelAuthority)
                                .addComponent(labelPosition))
                            .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textPosition)
                                .addComponent(textAuthority)))
                        .addGroup(frontPanelLayout.createSequentialGroup()
                            .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelSetSpeed)
                                .addComponent(labelSetAuthority))
                            .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textSetAuthority, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                .addComponent(textSetSpeed)))
                        .addGroup(frontPanelLayout.createSequentialGroup()
                            .addComponent(labelSpeed)
                            .addGap(4, 4, 4)
                            .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(frontPanelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(textSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                                .addComponent(labelTrainNum))))
                    .addGroup(frontPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(buttonReroute, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frontPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        frontPanelLayout.setVerticalGroup(
            frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frontPanelLayout.createSequentialGroup()
                .addComponent(labelTrainNum)
                .addGap(6, 6, 6)
                .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSpeed)
                    .addComponent(textSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAuthority)
                    .addComponent(textAuthority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPosition)
                    .addComponent(textPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSetSpeed)
                    .addComponent(textSetSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSetAuthority)
                    .addComponent(textSetAuthority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(buttonSend)
                .addGap(1, 1, 1)
                .addGroup(frontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRoute)
                    .addComponent(textRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(buttonReroute)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addComponent(frontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                    .addContainerGap(15, Short.MAX_VALUE)
                    .addComponent(frontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE)))
        );
        layeredPane.setLayer(backPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(frontPanel, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JLabel Background;
    protected javax.swing.JPanel backPanel;
    protected javax.swing.JButton buttonReroute;
    protected javax.swing.JButton buttonSend;
    protected javax.swing.JPanel frontPanel;
    protected javax.swing.JLabel labelAuthority;
    protected javax.swing.JLabel labelPosition;
    protected javax.swing.JLabel labelRoute;
    protected javax.swing.JLabel labelSetAuthority;
    protected javax.swing.JLabel labelSetSpeed;
    protected javax.swing.JLabel labelSpeed;
    protected javax.swing.JLabel labelTrainNum;
    protected javax.swing.JLayeredPane layeredPane;
    protected javax.swing.JSeparator separator;
    protected javax.swing.JTextField textAuthority;
    protected javax.swing.JTextField textPosition;
    protected javax.swing.JTextField textRoute;
    protected javax.swing.JTextField textSetAuthority;
    protected javax.swing.JTextField textSetSpeed;
    protected javax.swing.JTextField textSpeed;
    // End of variables declaration//GEN-END:variables
}
