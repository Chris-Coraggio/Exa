import javax.swing.JFrame;

public class ServerGUI extends javax.swing.JFrame {
	
    private javax.swing.JButton serverLaunchButton;
    private javax.swing.JLabel staticIPLabel;
    private javax.swing.JLabel dynamicIPLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea printOutTextArea;  

    public ServerGUI() {

        staticIPLabel = new javax.swing.JLabel();
        dynamicIPLabel = new javax.swing.JLabel();
        serverLaunchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        printOutTextArea = new javax.swing.JTextArea();

        staticIPLabel.setFont(new java.awt.Font("Perpetua", 1, 48));
        staticIPLabel.setText("Your IP:");

        dynamicIPLabel.setFont(new java.awt.Font("Perpetua", 1, 48));
        dynamicIPLabel.setText("1.1.1.1");

        serverLaunchButton.setText("Launch Server");

        printOutTextArea.setColumns(20);
        printOutTextArea.setRows(5);
        jScrollPane1.setViewportView(printOutTextArea);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(staticIPLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(dynamicIPLabel)))
                .addContainerGap(111, Short.MAX_VALUE))
            .addComponent(serverLaunchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(staticIPLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dynamicIPLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverLaunchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
        );
    }
    
    public void setIP(String address){
    	dynamicIPLabel.setText(address);
    }
    
    public void appendToOutput(String toAppend){
    	printOutTextArea.setText(printOutTextArea.getText() + "\n" + toAppend);
    }
}
