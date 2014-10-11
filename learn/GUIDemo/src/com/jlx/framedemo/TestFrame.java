package com.jlx.framedemo;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.cloudgarden.layout.AnchorLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import com.cloudgarden.layout.AnchorConstraint;
import javax.swing.WindowConstants;


/**
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* *************************************
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
* for this machine, so Jigloo or this code cannot be used legally
* for any corporate or commercial purpose.
* *************************************
*/
public class TestFrame extends javax.swing.JFrame {
	private JButton jButton1;
	private JButton jButton2;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JTextField jTextField2;
	private JTextField jTextField1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		TestFrame inst = new TestFrame();
		inst.setVisible(true);
	}
	
	public TestFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jLabel1 = new JLabel();
				this.getContentPane().add(
					jLabel1,
					new AnchorConstraint(
						272,
						389,
						349,
						238,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jLabel1.setPreferredSize(new java.awt.Dimension(59, 21));
				jLabel1.setText("\u7528\u6237\u540d\uff1a");
			}
			{
				jTextField1 = new JTextField();
				this.getContentPane().add(
					jTextField1,
					new AnchorConstraint(
						258,
						738,
						357,
						447,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jTextField1.setPreferredSize(new java.awt.Dimension(114, 27));
			}
			{
				jButton1 = new JButton();
				this.getContentPane().add(
					jButton1,
					new AnchorConstraint(
						642,
						473,
						741,
						174,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jButton1.setText("jButton1");
				jButton1.setPreferredSize(new java.awt.Dimension(117, 27));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton1ActionPerformed(evt);
					}
				});
			}
			{
				jButton2 = new JButton();
				this.getContentPane().add(
					jButton2,
					new AnchorConstraint(
						646,
						871,
						745,
						572,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jButton2.setText("jButton1");
				jButton2.setPreferredSize(new java.awt.Dimension(117, 27));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jButton2.actionPerformed, event="
							+ evt);
						JOptionPane.showMessageDialog(jButton2,"ddddddd");
						//TODO add your code for jButton2.actionPerformed
					}
				});
			}
			{
				jTextField2 = new JTextField();
				this.getContentPane().add(
					jTextField2,
					new AnchorConstraint(
						390,
						741,
						489,
						450,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jTextField2.setPreferredSize(new java.awt.Dimension(114, 27));
			}
			{
				jLabel2 = new JLabel();
				this.getContentPane().add(
					jLabel2,
					new AnchorConstraint(
						404,
						396,
						503,
						258,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jLabel2.setPreferredSize(new java.awt.Dimension(54, 27));
				jLabel2.setText("\u5bc6\u7801\uff1a");
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {
		jTextField2.setText("you click boutton1");
//TODO add your code for jButton1.actionPerformed
	}

}
