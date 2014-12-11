
import java.util.LinkedList;
import javax.swing.JFrame;

/**@author Dean Mauro*/

public class Office extends javax.swing.JPanel {
    private static boolean MovingOrFixed = false;
    protected LinkedList<Train> trainsOnTracks = new LinkedList<>();


    public Office() {
    // <editor-fold defaultstate="collapsed" desc="Constructor">
        
        initComponents();
        TrainsScrollPane.getViewport().setOpaque(false);
        TrainsScrollPane.setBorder(null);
        
        screenFrontPanel.add(new TrackModelInterface());
        screenFrontPanel.revalidate();
        screenFrontPanel.repaint();
        
    }
    // </editor-fold> 

    public void addTrain(int trainID){
    // <editor-fold defaultstate="collapsed" desc="Add Train">
        
        trainsOnTracks.add(new Train(trainID));
        innerPanel.add(trainsOnTracks.getLast());
        innerPanel.revalidate();
        innerPanel.repaint();
    }
    // </editor-fold> 
    
    public void removeTrain(int num){
    // <editor-fold defaultstate="collapsed" desc="Remove Train"> 
        
        innerPanel.remove(trainsOnTracks.removeLast());
        innerPanel.revalidate();
        innerPanel.repaint();
    }// </editor-fold> 
    
    public double getSuggestedAuthority(int ID){
        return Train.getAuthority(trainsOnTracks.get(ID));
    }
    
    public double getSuggestedSpeed(int ID){
        return Train.getSpeed(trainsOnTracks.get(ID));
    }
    
    public double getSuggestedPosition(int ID){
        return Train.getPosition(trainsOnTracks.get(ID));
    }
    
    public void setMovingBlock(){
    // <editor-fold defaultstate="collapsed" desc="Moving Block"> 
        MovingOrFixed = true;
        radioMoving.setSelected(true);
    }
    // </editor-fold> 
    
    public void setFixedBlock(){
    // <editor-fold defaultstate="collapsed" desc="Fixed Block"> 
        MovingOrFixed = false;
        radioFixed.setSelected(true);
    }
    // </editor-fold> 
      
    public static void main(String args[]){
    // <editor-fold defaultstate="collapsed" desc="Main"> 
        
    JFrame frame1 = new JFrame("Office");
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.add(new Office());
    frame1.pack();

    frame1.setVisible(true);
    frame1.validate();
    }
    // </editor-fold> 

    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttongroupMovingFixed = new javax.swing.ButtonGroup();
        layeredPane = new javax.swing.JLayeredPane();
        topPanel = new javax.swing.JPanel();
        TrainsScrollPane = new javax.swing.JScrollPane();
        innerPanel = new javax.swing.JPanel();
        clockLayeredPane = new javax.swing.JLayeredPane();
        clockFrontPanel = new javax.swing.JPanel();
        textClock = new javax.swing.JTextField();
        buttonDecreaseClockSpeed = new javax.swing.JButton();
        buttonIncreaseClockSpeed = new javax.swing.JButton();
        clockBackPanel = new javax.swing.JPanel();
        clockBackground = new javax.swing.JLabel();
        workersLayeredPane = new javax.swing.JLayeredPane();
        workersFrontPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textSchedule = new javax.swing.JTextArea();
        workersBackPanel = new javax.swing.JPanel();
        workersBackground = new javax.swing.JLabel();
        screenLayeredPane = new javax.swing.JLayeredPane();
        screenFrontPanel = new javax.swing.JPanel();
        MBOLayeredPane = new javax.swing.JLayeredPane();
        MBOFrontPanel = new javax.swing.JPanel();
        labelMoving = new javax.swing.JLabel();
        labelFixed = new javax.swing.JLabel();
        radioMoving = new javax.swing.JRadioButton();
        radioFixed = new javax.swing.JRadioButton();
        MBOBackPanel = new javax.swing.JPanel();
        MBOBackground = new javax.swing.JLabel();
        schedulerLayeredPane = new javax.swing.JLayeredPane();
        schedulerFrontPanel = new javax.swing.JPanel();
        textSchedulerBlock = new javax.swing.JTextField();
        buttonGo = new javax.swing.JButton();
        schedulerBackPanel = new javax.swing.JPanel();
        clockBackground4 = new javax.swing.JLabel();
        backPanel = new javax.swing.JPanel();
        background = new javax.swing.JLabel();

        setOpaque(false);

        layeredPane.setPreferredSize(new java.awt.Dimension(1175, 1038));
        layeredPane.setSize(new java.awt.Dimension(1175, 1038));

        topPanel.setBackground(new java.awt.Color(239, 241, 245));
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new java.awt.Dimension(920, 545));
        topPanel.setSize(new java.awt.Dimension(920, 545));

        TrainsScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        TrainsScrollPane.setOpaque(false);
        TrainsScrollPane.setViewportView(innerPanel);

        innerPanel.setOpaque(false);
        TrainsScrollPane.setViewportView(innerPanel);

        clockFrontPanel.setOpaque(false);

        textClock.setBackground(new java.awt.Color(204, 255, 204));
        textClock.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        textClock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textClock.setText("0:00");

        buttonDecreaseClockSpeed.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        buttonDecreaseClockSpeed.setText("<<");

        buttonIncreaseClockSpeed.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        buttonIncreaseClockSpeed.setText(">>");

        javax.swing.GroupLayout clockFrontPanelLayout = new javax.swing.GroupLayout(clockFrontPanel);
        clockFrontPanel.setLayout(clockFrontPanelLayout);
        clockFrontPanelLayout.setHorizontalGroup(
            clockFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockFrontPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(clockFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textClock, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(clockFrontPanelLayout.createSequentialGroup()
                        .addComponent(buttonDecreaseClockSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonIncreaseClockSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        clockFrontPanelLayout.setVerticalGroup(
            clockFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockFrontPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(textClock, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clockFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDecreaseClockSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIncreaseClockSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        clockBackPanel.setOpaque(false);

        clockBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clockBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clock.png"))); // NOI18N

        javax.swing.GroupLayout clockBackPanelLayout = new javax.swing.GroupLayout(clockBackPanel);
        clockBackPanel.setLayout(clockBackPanelLayout);
        clockBackPanelLayout.setHorizontalGroup(
            clockBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
            .addGroup(clockBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(clockBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE))
        );
        clockBackPanelLayout.setVerticalGroup(
            clockBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
            .addGroup(clockBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(clockBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 114, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout clockLayeredPaneLayout = new javax.swing.GroupLayout(clockLayeredPane);
        clockLayeredPane.setLayout(clockLayeredPaneLayout);
        clockLayeredPaneLayout.setHorizontalGroup(
            clockLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockLayeredPaneLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(clockBackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(clockLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(clockFrontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        clockLayeredPaneLayout.setVerticalGroup(
            clockLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clockBackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(clockLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(clockFrontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        clockLayeredPane.setLayer(clockFrontPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        clockLayeredPane.setLayer(clockBackPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        workersFrontPanel.setOpaque(false);

        textSchedule.setColumns(12);
        textSchedule.setRows(2);
        jScrollPane1.setViewportView(textSchedule);

        javax.swing.GroupLayout workersFrontPanelLayout = new javax.swing.GroupLayout(workersFrontPanel);
        workersFrontPanel.setLayout(workersFrontPanelLayout);
        workersFrontPanelLayout.setHorizontalGroup(
            workersFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workersFrontPanelLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        workersFrontPanelLayout.setVerticalGroup(
            workersFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workersFrontPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        workersBackPanel.setOpaque(false);

        workersBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Schedule.png"))); // NOI18N

        javax.swing.GroupLayout workersBackPanelLayout = new javax.swing.GroupLayout(workersBackPanel);
        workersBackPanel.setLayout(workersBackPanelLayout);
        workersBackPanelLayout.setHorizontalGroup(
            workersBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(workersBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(workersBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        workersBackPanelLayout.setVerticalGroup(
            workersBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
            .addGroup(workersBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(workersBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout workersLayeredPaneLayout = new javax.swing.GroupLayout(workersLayeredPane);
        workersLayeredPane.setLayout(workersLayeredPaneLayout);
        workersLayeredPaneLayout.setHorizontalGroup(
            workersLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workersLayeredPaneLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(workersBackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(workersLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(workersFrontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        workersLayeredPaneLayout.setVerticalGroup(
            workersLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(workersBackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(workersLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(workersFrontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        workersLayeredPane.setLayer(workersFrontPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        workersLayeredPane.setLayer(workersBackPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        screenFrontPanel.setOpaque(false);

        javax.swing.GroupLayout screenFrontPanelLayout = new javax.swing.GroupLayout(screenFrontPanel);
        screenFrontPanel.setLayout(screenFrontPanelLayout);
        screenFrontPanelLayout.setHorizontalGroup(
            screenFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );
        screenFrontPanelLayout.setVerticalGroup(
            screenFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout screenLayeredPaneLayout = new javax.swing.GroupLayout(screenLayeredPane);
        screenLayeredPane.setLayout(screenLayeredPaneLayout);
        screenLayeredPaneLayout.setHorizontalGroup(
            screenLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(screenFrontPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        screenLayeredPaneLayout.setVerticalGroup(
            screenLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(screenLayeredPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(screenFrontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        screenLayeredPane.setLayer(screenFrontPanel, javax.swing.JLayeredPane.PALETTE_LAYER);

        MBOFrontPanel.setOpaque(false);

        labelMoving.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelMoving.setText("Moving Block:");

        labelFixed.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelFixed.setText("Fixed Block:");

        buttongroupMovingFixed.add(radioMoving);
        radioMoving.setEnabled(false);

        buttongroupMovingFixed.add(radioFixed);
        radioFixed.setSelected(true);
        radioFixed.setEnabled(false);

        javax.swing.GroupLayout MBOFrontPanelLayout = new javax.swing.GroupLayout(MBOFrontPanel);
        MBOFrontPanel.setLayout(MBOFrontPanelLayout);
        MBOFrontPanelLayout.setHorizontalGroup(
            MBOFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MBOFrontPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(MBOFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MBOFrontPanelLayout.createSequentialGroup()
                        .addComponent(labelFixed, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(11, 11, 11)
                .addGroup(MBOFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioFixed)
                    .addComponent(radioMoving))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        MBOFrontPanelLayout.setVerticalGroup(
            MBOFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MBOFrontPanelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(MBOFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MBOFrontPanelLayout.createSequentialGroup()
                        .addComponent(radioMoving)
                        .addGap(4, 4, 4)))
                .addGroup(MBOFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFixed, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MBOFrontPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(radioFixed)))
                .addGap(38, 38, 38))
        );

        MBOBackPanel.setOpaque(false);

        MBOBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MBOBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MBO.png"))); // NOI18N

        javax.swing.GroupLayout MBOBackPanelLayout = new javax.swing.GroupLayout(MBOBackPanel);
        MBOBackPanel.setLayout(MBOBackPanelLayout);
        MBOBackPanelLayout.setHorizontalGroup(
            MBOBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
            .addGroup(MBOBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(MBOBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 123, Short.MAX_VALUE))
        );
        MBOBackPanelLayout.setVerticalGroup(
            MBOBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 139, Short.MAX_VALUE)
            .addGroup(MBOBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(MBOBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 114, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MBOLayeredPaneLayout = new javax.swing.GroupLayout(MBOLayeredPane);
        MBOLayeredPane.setLayout(MBOLayeredPaneLayout);
        MBOLayeredPaneLayout.setHorizontalGroup(
            MBOLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MBOLayeredPaneLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(MBOBackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(MBOLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(MBOFrontPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MBOLayeredPaneLayout.setVerticalGroup(
            MBOLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MBOBackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MBOLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(MBOFrontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MBOLayeredPane.setLayer(MBOFrontPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        MBOLayeredPane.setLayer(MBOBackPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        schedulerFrontPanel.setOpaque(false);

        buttonGo.setText("Go!");

        javax.swing.GroupLayout schedulerFrontPanelLayout = new javax.swing.GroupLayout(schedulerFrontPanel);
        schedulerFrontPanel.setLayout(schedulerFrontPanelLayout);
        schedulerFrontPanelLayout.setHorizontalGroup(
            schedulerFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(schedulerFrontPanelLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(schedulerFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, schedulerFrontPanelLayout.createSequentialGroup()
                        .addComponent(textSchedulerBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, schedulerFrontPanelLayout.createSequentialGroup()
                        .addComponent(buttonGo)
                        .addGap(35, 35, 35))))
        );
        schedulerFrontPanelLayout.setVerticalGroup(
            schedulerFrontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(schedulerFrontPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(textSchedulerBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(buttonGo)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        schedulerBackPanel.setOpaque(false);

        clockBackground4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clockBackground4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewTrain.png"))); // NOI18N
        clockBackground4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout schedulerBackPanelLayout = new javax.swing.GroupLayout(schedulerBackPanel);
        schedulerBackPanel.setLayout(schedulerBackPanelLayout);
        schedulerBackPanelLayout.setHorizontalGroup(
            schedulerBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(schedulerBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(clockBackground4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        schedulerBackPanelLayout.setVerticalGroup(
            schedulerBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(schedulerBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(clockBackground4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout schedulerLayeredPaneLayout = new javax.swing.GroupLayout(schedulerLayeredPane);
        schedulerLayeredPane.setLayout(schedulerLayeredPaneLayout);
        schedulerLayeredPaneLayout.setHorizontalGroup(
            schedulerLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(schedulerLayeredPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(schedulerBackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(schedulerLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, schedulerLayeredPaneLayout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addComponent(schedulerFrontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24)))
        );
        schedulerLayeredPaneLayout.setVerticalGroup(
            schedulerLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(schedulerBackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(schedulerLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(schedulerLayeredPaneLayout.createSequentialGroup()
                    .addComponent(schedulerFrontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 16, Short.MAX_VALUE)))
        );
        schedulerLayeredPane.setLayer(schedulerFrontPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        schedulerLayeredPane.setLayer(schedulerBackPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clockLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrainsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(workersLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(955, Short.MAX_VALUE)))
            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup()
                    .addGap(222, 222, 222)
                    .addComponent(screenLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(252, Short.MAX_VALUE)))
            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup()
                    .addGap(921, 921, 921)
                    .addComponent(MBOLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE)))
            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                    .addContainerGap(918, Short.MAX_VALUE)
                    .addComponent(schedulerLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(41, 41, 41)))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(clockLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(TrainsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup()
                    .addGap(171, 171, 171)
                    .addComponent(workersLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(366, Short.MAX_VALUE)))
            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(screenLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(256, Short.MAX_VALUE)))
            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(MBOLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(544, Short.MAX_VALUE)))
            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup()
                    .addGap(173, 173, 173)
                    .addComponent(schedulerLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(377, Short.MAX_VALUE)))
        );

        backPanel.setBackground(new java.awt.Color(239, 241, 245));

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        background.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        background.setPreferredSize(new java.awt.Dimension(1175, 1032));
        background.setSize(new java.awt.Dimension(1175, 1032));

        javax.swing.GroupLayout backPanelLayout = new javax.swing.GroupLayout(backPanel);
        backPanel.setLayout(backPanelLayout);
        backPanelLayout.setHorizontalGroup(
            backPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        backPanelLayout.setVerticalGroup(
            backPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 700, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(topPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE))
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneLayout.createSequentialGroup()
                    .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layeredPane.setLayer(topPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        layeredPane.setLayer(backPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel MBOBackPanel;
    protected javax.swing.JLabel MBOBackground;
    protected javax.swing.JPanel MBOFrontPanel;
    protected javax.swing.JLayeredPane MBOLayeredPane;
    protected javax.swing.JScrollPane TrainsScrollPane;
    protected javax.swing.JPanel backPanel;
    protected javax.swing.JLabel background;
    protected javax.swing.JButton buttonDecreaseClockSpeed;
    protected javax.swing.JButton buttonGo;
    protected javax.swing.JButton buttonIncreaseClockSpeed;
    protected javax.swing.ButtonGroup buttongroupMovingFixed;
    protected javax.swing.JPanel clockBackPanel;
    protected javax.swing.JLabel clockBackground;
    protected javax.swing.JLabel clockBackground4;
    protected javax.swing.JPanel clockFrontPanel;
    protected javax.swing.JLayeredPane clockLayeredPane;
    protected javax.swing.JPanel innerPanel;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JLabel labelFixed;
    protected javax.swing.JLabel labelMoving;
    protected javax.swing.JLayeredPane layeredPane;
    protected javax.swing.JRadioButton radioFixed;
    protected javax.swing.JRadioButton radioMoving;
    protected javax.swing.JPanel schedulerBackPanel;
    protected javax.swing.JPanel schedulerFrontPanel;
    protected javax.swing.JLayeredPane schedulerLayeredPane;
    protected javax.swing.JPanel screenFrontPanel;
    protected javax.swing.JLayeredPane screenLayeredPane;
    protected static javax.swing.JTextField textClock;
    protected javax.swing.JTextArea textSchedule;
    protected javax.swing.JTextField textSchedulerBlock;
    protected javax.swing.JPanel topPanel;
    protected javax.swing.JPanel workersBackPanel;
    protected javax.swing.JLabel workersBackground;
    protected javax.swing.JPanel workersFrontPanel;
    protected javax.swing.JLayeredPane workersLayeredPane;
    // End of variables declaration//GEN-END:variables
}
