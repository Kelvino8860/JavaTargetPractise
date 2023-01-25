import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

 //Fix bugs
public class JavaTargetPractise extends JFrame implements MouseListener
{
     private JPanel[] arrayPanel = new JPanel[100];
     private final int COL = 10;
     private final int ROW = 10;
     private JPanel mainPanel = new JPanel(new GridLayout(ROW,COL));
     private final String str = "X";
     private final String hit = "HIT";
     private JLabel[] label = new JLabel[5];
     private Container con = getContentPane();
     private int labelNumber;

     public JavaTargetPractise()
     {
        setTitle("JTargetPractise");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        con.add(mainPanel);
        addTragetPanels(arrayPanel, label, str);
        displayTarget(arrayPanel,label);
     }

/*The method creates 100 JPanels and adds them in the
 * main panel and JLabels
 */
public void addTragetPanels(JPanel[] array, JLabel[] label, String str)
{
    int i;

    //Add panels to main frame
    for(i = 0; i < array.length; ++i)
    {
        array[i] = new JPanel();
        mainPanel.add(array[i]);
        array[i].addMouseListener(this);
    }

    //Initialize 5 JLabels with a string
    for(i = 0; i < label.length; ++i)
    {
        label[i] = new JLabel(str);
        label[i].setFont(new Font("Arial",Font.BOLD,16));
    }
}

/*This method selects targets randomly and places
 * the labels "X" in the random panels 
 */
public void displayTarget(JPanel[] array, JLabel[] label)
{
      int numTargets = 5;
      int i,j;
      int count = 0;
      ArrayList<Integer> list = new ArrayList<>(5);

      //Randomly select five numbers between 0 to 99
      for(i = 0; i < numTargets; ++i)
      {
         j = (int)(Math.random()*100);

         //Ensure a random number is not repeated
         //Ensure a random panel is not choosen more than once
         for(int k = 0; k < list.size(); ++k)
         {
            if(j == list.get(k))
            {
                count++;
                break;
            }
         }

         //If their is no repetition then add the random number to
         //the array
         if(count == 0)
         {
           list.add(j);
         }
         else
         {
            list.remove(i);
            i = i;
         }
      }
         
      for(i = 0; i < list.size(); ++i)
      {
          array[list.get(i)].add(label[i]);
      }
}

@Override
public void mouseClicked(MouseEvent e)
{
    int whichButt = e.getButton();
    Object source = e.getComponent();
    JPanel selectedPanel = (JPanel) source;
    int index = 0;

    if(whichButt == MouseEvent.BUTTON1)
    {
        for(int i = 0; i < arrayPanel.length; ++i)
        {
            if(selectedPanel == arrayPanel[i])
            {
                index = i;
                break;
            }
        }

        //System.out.println(labelNumber);
        try
        {
           Object[] selectedLabel = selectedPanel.getComponents();
           JLabel hitLabel = (JLabel) selectedLabel[0];
           hitLabel.setText(hit);
        }
        catch(ArrayIndexOutOfBoundsException g)
        {
          JOptionPane.showMessageDialog(null, g);   
        }
    }
}
@Override
public void mouseEntered(MouseEvent e)
{

}
@Override
public void mouseExited(MouseEvent e)
{

}
@Override
public void mousePressed(MouseEvent e)
{

}
@Override
public void mouseReleased(MouseEvent e)
{

}

public static void main(String[] args)
{
    JavaTargetPractise aFrame = new JavaTargetPractise();
    aFrame.setBounds(300,300,400,400);
    aFrame.setVisible(true);
}
}