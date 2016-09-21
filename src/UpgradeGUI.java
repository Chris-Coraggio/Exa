import javax.swing.*;

/*
 * Upgrade GUI
 * 
 * This class generates a GUI by which players initially modify their stats based on how many points they have to work with
 * Created with NetBeans GUI builder
 * 
 * Properties to modify:
 * Health
 * Weapons
 * Shields
 * Speed
 */


public class UpgradeGUI extends JDialog{
	
	private final int TOTAL_POINTS = 200;
	private JSlider[] sliders;
	private JLabel[] labels;
	private JSlider moving = new JSlider();
	private boolean isFinished;
	
    private JButton continueButton;
    private JLabel titleLabel;
    
    private JLabel pointsRemainingLabel;
    private JLabel realTimePointsRemainingLabel;
    
    private JLabel healthDescLabel;
    private JLabel weaponsDescLabel;
    private JLabel shieldsDescLabel;
    private JLabel speedDescLabel;
    
    private JLabel healthLabel;
    private JLabel weaponsLabel;
    private JLabel shieldsLabel;
    private JLabel speedLabel;
    
    private JLabel realTimeShieldsLabel;
    private JLabel realTimeHealthLabel;
    private JLabel realTimeWeaponsLabel;
    private JLabel realTimeSpeedLabel;
    
    private JSlider healthSlider;
    private JSlider weaponsSlider;
    private JSlider shieldsSlider;
    private JSlider speedSlider; 

	    public UpgradeGUI() {
	        initComponents();
	        isFinished = false;
	        this.setVisible(true);
	        while(! isFinished()){
	        	if(! isFinished()) checkSliders();
	        	else break;
	        }
	    }
	               
	    private void initComponents() {
	    	
	        healthSlider = new JSlider();
	        weaponsSlider = new JSlider();
	        shieldsSlider = new JSlider();
	        speedSlider = new JSlider();
	        
	        healthSlider.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	        weaponsSlider.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	        shieldsSlider.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	        speedSlider.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	        
	        titleLabel = new JLabel();
	        healthLabel = new JLabel();
	        weaponsLabel = new JLabel();
	        shieldsLabel = new JLabel();
	        speedLabel = new JLabel();
	        
	        realTimeHealthLabel = new JLabel();
	        realTimeWeaponsLabel = new JLabel();
	        realTimeShieldsLabel = new JLabel();
	        realTimeSpeedLabel = new JLabel();
	        
	        pointsRemainingLabel = new JLabel();
	        realTimePointsRemainingLabel = new JLabel();
	        
	        healthDescLabel = new JLabel();
	        weaponsDescLabel = new JLabel();
	        shieldsDescLabel = new JLabel();
	        speedDescLabel = new JLabel();
	        
	        continueButton = new JButton();
	        
	        initSlidersAndLabels();

	        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	        titleLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 24));
	        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        titleLabel.setText("EXA Upgrader - Choose how to distribute your points to customize your own ship");
	        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);

	        healthLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        healthLabel.setText("Health:");

	        weaponsLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        weaponsLabel.setText("Weapons:");

	        shieldsLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        shieldsLabel.setText("Shields:");

	        speedLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        speedLabel.setText("Speed:");

	        realTimeShieldsLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        realTimeShieldsLabel.setHorizontalAlignment(SwingConstants.CENTER);

	        realTimeHealthLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        realTimeHealthLabel.setHorizontalAlignment(SwingConstants.CENTER);

	        realTimeWeaponsLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        realTimeWeaponsLabel.setHorizontalAlignment(SwingConstants.CENTER);

	        realTimeSpeedLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        realTimeSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);

	        pointsRemainingLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        pointsRemainingLabel.setText("Points Remaining:");

	        realTimePointsRemainingLabel.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); 
	        realTimePointsRemainingLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        realTimePointsRemainingLabel.setText("" + TOTAL_POINTS);

	        healthDescLabel.setText("The raw ability of your ship to take damage");
	        weaponsDescLabel.setText("The ability of your ship to regenerate after taking damage");
	        shieldsDescLabel.setText("The damage your ship's bullets inflict on other ships");
	        speedDescLabel.setText("How fast your ship can potentially go");

	        continueButton.setLabel("Continue to Game");
	        continueButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                continueButtonPressed(evt);
	            }
	        });
	        
	        createGUI(); //calls GUI builder junk
	    }

	    private void continueButtonPressed(java.awt.event.ActionEvent evt) {                                         
	        //Continue to Game
	    	isFinished = true;
	    	this.dispose();
	    }   
	    
	    public boolean isFinished(){
	    	return isFinished;
	    }
	    
	    public int[] getNumbers(){
	    	int [] nums = new int[4];
	    	for(int i = 0; i < 4; i++){
	    		nums[i] = sliders[i].getValue();
	    	}
	    	return nums;
	    }
	    
	    private void initSlidersAndLabels(){
	    	sliders = new JSlider[4];
	    	sliders[0] = healthSlider;
	    	sliders[1] = weaponsSlider;
	    	sliders[2] = shieldsSlider;
	    	sliders[3] = speedSlider;
	    	labels = new JLabel[4];
	    	labels[0] = realTimeHealthLabel;
	    	labels[1] = realTimeWeaponsLabel;
	    	labels[2] = realTimeShieldsLabel;
	    	labels[3] = realTimeSpeedLabel;
	    	for(JLabel j : labels) j.setText("0");
	    }
	    
	    private void checkSliders(){
	    	int total; //overall total
	    	int totalWithoutMovingOne; //total of static ones
	    	for(JSlider j : sliders){
	    		total = 0;
	    		totalWithoutMovingOne = 0;
	    		if((! j.getValueIsAdjusting()) && moving.equals(j)){
	    			
	    			for(JSlider k : sliders){
	    				total += k.getValue();
	    				totalWithoutMovingOne += k.getValue();
	    				if(k.equals(j)) totalWithoutMovingOne -= k.getValue();
	    			}
	    			if(TOTAL_POINTS < total){
	    				j.setValue(TOTAL_POINTS - totalWithoutMovingOne);
	    				getLabel(j).setText("" + (TOTAL_POINTS - totalWithoutMovingOne));
	    				realTimePointsRemainingLabel.setText("0");
	    			}else{
	    				getLabel(j).setText("" + j.getValue());
	    				realTimePointsRemainingLabel.setText("" + (TOTAL_POINTS - total));
	    			}
	    			moving = new JSlider();
	    		}
	    		if(j.getValueIsAdjusting()) moving = j;
	    	}
	    }
	    
	    private JLabel getLabel(JSlider j){
	    	for(int i = 0; i < sliders.length; i++){
	    		if(sliders[i].equals(j)) return labels[i];
	    	}
	    	return null;
	    }
	    
	    private void createGUI(){
	    	GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(healthLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(healthSlider, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
	                                .addGap(18, 18, 18)
	                                .addComponent(realTimeHealthLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                .addGap(18, 18, 18)
	                                .addComponent(healthDescLabel))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                                    .addComponent(weaponsLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(speedLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(shieldsLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(shieldsSlider, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(realTimeShieldsLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(shieldsDescLabel))
	                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                                                .addComponent(pointsRemainingLabel, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
	                                            .addGap(18, 18, 18)
	                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                                                .addGroup(layout.createSequentialGroup()
	                                                    .addComponent(realTimeSpeedLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                                    .addGap(18, 18, 18)
	                                                    .addComponent(speedDescLabel))
	                                                .addGroup(layout.createSequentialGroup()
	                                                    .addComponent(realTimePointsRemainingLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                    .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
	                                                    .addGap(0, 0, Short.MAX_VALUE))))
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addComponent(weaponsSlider, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
	                                            .addGap(18, 18, 18)
	                                            .addComponent(realTimeWeaponsLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                            .addGap(18, 18, 18)
	                                            .addComponent(weaponsDescLabel))))))
	                        .addContainerGap(16, Short.MAX_VALUE))))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(healthLabel)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                        .addComponent(realTimeHealthLabel)
	                        .addComponent(healthDescLabel))
	                    .addComponent(healthSlider, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                        .addComponent(realTimeShieldsLabel)
	                        .addComponent(shieldsDescLabel))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                                        .addComponent(realTimeWeaponsLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
	                                        .addComponent(weaponsDescLabel))
	                                    .addComponent(weaponsSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(shieldsLabel)
	                                .addGap(18, 18, 18)))
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(shieldsSlider, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(weaponsLabel, GroupLayout.Alignment.TRAILING))))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                        .addComponent(realTimeSpeedLabel)
	                        .addComponent(speedDescLabel))
	                    .addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(speedLabel))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(pointsRemainingLabel)
	                    .addComponent(realTimePointsRemainingLabel)
	                    .addComponent(continueButton))
	                .addContainerGap(14, Short.MAX_VALUE))
	        );
	        pack();
	    }// end createGUI                   
	}
