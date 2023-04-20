package Server;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame { //unsure why this throws error
    private JButton bDeleteFire;
    private JButton bMoveDrone;
    private JButton bRecallAll;
    private JButton bShutdown;
    private JLabel lFunctions;
    private JLabel lInformation;
    private JPanel pFunctions;
    private JPanel pInformation;
    private JPanel pMap;
    private JPanel pWestPane;
    public JTextField tInformation;

    public GUI() {
        initComponents();
    }
                         
    public void initComponents() {

        //create components
        pWestPane = new JPanel();
        pFunctions = new JPanel();
        lFunctions = new JLabel();
        bDeleteFire = new JButton();
        bMoveDrone = new JButton();
        bRecallAll = new JButton();
        bShutdown = new JButton();
        pInformation = new JPanel();
        lInformation = new JLabel();
        tInformation = new JTextField();
        pMap = new JPanel();

        //set the x button to close the applications
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //change this to call the shutdown() function?

        //set text for labels and buttons
        lInformation.setText("Info and Messages");
        lFunctions.setText("Functional Controls");
        bDeleteFire.setText("Delete Fire");
        bMoveDrone.setText("Move Drone");
        bRecallAll.setText("Recall All");
        bShutdown.setText("Shutdown");

        //set borders for the panes
        pMap.setBorder(BorderFactory.createLineBorder(null));
        pWestPane.setBorder(BorderFactory.createLineBorder(null));

        //layout setters and component adding for each pane
        GroupLayout pFunctionsLayout = new GroupLayout(pFunctions);
        pFunctions.setLayout(pFunctionsLayout);
        pFunctionsLayout.setHorizontalGroup(
            pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pFunctionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lFunctions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pFunctionsLayout.createSequentialGroup()
                        .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(bDeleteFire, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bMoveDrone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(pFunctionsLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bShutdown))
                            .addGroup(pFunctionsLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(bRecallAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        pFunctionsLayout.setVerticalGroup(
            pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pFunctionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lFunctions)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bDeleteFire)
                    .addComponent(bRecallAll))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bMoveDrone)
                    .addComponent(bShutdown))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout pInformationLayout = new GroupLayout(pInformation);
        pInformation.setLayout(pInformationLayout);
        pInformationLayout.setHorizontalGroup(
            pInformationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pInformationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(tInformation)
                    .addGroup(pInformationLayout.createSequentialGroup()
                        .addComponent(lInformation)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pInformationLayout.setVerticalGroup(
            pInformationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lInformation)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tInformation, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout pWestPaneLayout = new GroupLayout(pWestPane);
        pWestPane.setLayout(pWestPaneLayout);
        pWestPaneLayout.setHorizontalGroup(
            pWestPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pWestPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pWestPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(pInformation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pFunctions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pWestPaneLayout.setVerticalGroup(
            pWestPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pWestPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pFunctions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pInformation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        GroupLayout pMapLayout = new GroupLayout(pMap);
        pMap.setLayout(pMapLayout);
        pMapLayout.setHorizontalGroup(
            pMapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );
        pMapLayout.setVerticalGroup(
            pMapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pWestPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pMap, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(pMap, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pWestPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        //end of layout setting and component adding

        //button action listeners
        bDeleteFire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteFireActionPerformed(evt);
            }
        });
        
        bRecallAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRecallAllActionPerformed(evt);
            }
        });

        bMoveDrone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMoveDroneActionPerformed(evt);
            }
        });
        
        bShutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bShutdownActionPerformed(evt);
            }
        });
    
        pack();
    }                      

    private void bDeleteFireActionPerformed(java.awt.event.ActionEvent evt) {                                            
        Server.deleteFire();
    }                                           

    private void bMoveDroneActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Server.moveDrone();
    } 

    private void bRecallAllActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Server.recallAll();
    }                                            

    private void bShutdownActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Server.shutdown();
    }                     
    
    public void addMessageToConsole(String message) {

        System.out.println(message);

        String textFieldContent = tInformation.getText();

        tInformation.setText(textFieldContent + "\n" + message);

    }

    //main() code for debugging/running GUI
    // public static void main(String args[]) {
    //     // try {
    //     //     for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    //     //         if ("Nimbus".equals(info.getName())) {
    //     //             UIManager.setLookAndFeel(info.getClassName());
    //     //             break;
    //     //         }
    //     //     }
    //     // } 
    //     // catch (ClassNotFoundException ex) {
    //     //     java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //     // } 
    //     // catch (InstantiationException ex) {
    //     //     java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //     // } 
    //     // catch (IllegalAccessException ex) {
    //     //     java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //     // } 
    //     // catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //     //     java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //     // }

    //     // create and display form
    //     // EventQueue.invokeLater(new Runnable() {
    //     //     public void run() {
    //     //         new GUI().setVisible(true);
    //     //     }
    //     // });
    // }  
}
