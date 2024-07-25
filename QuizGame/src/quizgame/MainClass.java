package quizgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Questions
{
    private String ques;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String ans;
    
    public Questions(String q,String a,String b,String c,String d,String ans)
    {
        ques=q;
        op1=a;
        op2=b;
        op3=c;
        op4=d;
        this.ans=ans;
    }
    public String getQues()
    {
        return ques;
    }
    public String getOptionA()
    {
        return op1;
    }
    public String getOptionB()
    {
        return op2;
    }
    public String getOptionC()
    {
        return op3;
    }
    public String getOptionD()
    {
        return op4;
    }
    public void setAns(String s)
    {
        ans=s;
    }
    public String getAns()
    {
        return ans;
    }
}

class MyPanel extends JPanel implements ActionListener
{
    private JLabel lblQuesNo,lblA,lblB,lblC,lblD,lblWelcome,lblCaption,lblEnd,lblScore;
    private JTextArea txtQuestion;
    private JButton btnA,btnB,btnC,btnD,btnNext,btnStart,btnSubmit,btnExit;
    MyPanel p;
    ArrayList<Questions> questions=new ArrayList<>();
    File path;
    private static int i,score=0;
    
    public JLabel makeLabel(int y)
    {
        JLabel t= new JLabel();
        t.setBounds(200, y, 300, 40);
        t.setOpaque(true);
        t.setVerticalAlignment(JLabel.CENTER);
        t.setHorizontalAlignment(JLabel.CENTER);
        t.setFont(new Font("Courier New",3,20));
        t.setVisible(false);
        super.add(t);
        return t;
    }
    public JButton makeButton(String s,int y)
    {
        JButton t = new JButton();
        t.setText(s);
        t.setBounds(100, y, 50, 40);
        t.setVerticalAlignment(JLabel.CENTER);
        t.setHorizontalAlignment(JLabel.CENTER);
        t.setBackground(new Color(0,153,153));
        t.setFont(new Font("Courier New",3,20));
        t.setVisible(false);
        super.add(t);
        return t;
    }
    
    public MyPanel()
    {
        p=this;
        path= new File("Questions.csv");
        
        lblEnd= new JLabel();
        lblEnd.setText("Your Score");
        lblEnd.setVerticalAlignment(JLabel.CENTER);
        lblEnd.setHorizontalAlignment(JLabel.CENTER);
        lblEnd.setForeground(new Color(229,255,204));
        lblEnd.setVisible(false);
        lblEnd.setBounds(100, 50, 500, 250);
        lblEnd.setFont(new Font("Times New Roman",3,60));
        super.add(lblEnd);
        
        lblScore=new JLabel();
        lblScore.setVerticalAlignment(JLabel.CENTER);
        lblScore.setHorizontalAlignment(JLabel.CENTER);
        lblScore.setForeground(new Color(229,255,204));
        lblScore.setVisible(false);
        lblScore.setBounds(100, 150, 500, 250);
        lblScore.setFont(new Font("Times New Roman",3,60));
        super.add(lblScore);
        
        
        lblWelcome = new JLabel();
        lblWelcome.setText("GRAY MATTER");
        lblWelcome.setVerticalAlignment(JLabel.CENTER);
        lblWelcome.setHorizontalAlignment(JLabel.CENTER);
        lblWelcome.setBackground(new Color(0,0,153));
        lblWelcome.setForeground(new Color(229,255,204));
        lblWelcome.setOpaque(true);
        lblWelcome.setBounds(100, 50, 500, 250);
        lblWelcome.setFont(new Font("Times New Roman",3,60));
        super.add(lblWelcome);
        
        lblCaption=new JLabel();
        lblCaption.setText("For Those Who Thinks");
        lblCaption.setVerticalAlignment(JLabel.CENTER);
        lblCaption.setHorizontalAlignment(JLabel.CENTER);
        lblCaption.setForeground(new Color(0,0,0));
        lblCaption.setFont(new Font("Ariel", 3, 20));
        lblCaption.setBounds(100, 200, 500, 250);
        super.add(lblCaption);
        
        btnStart=new JButton();
        btnStart.setText("START Quiz");
        btnStart.setBounds(200,400,300,100);
        btnStart.setVerticalAlignment(JLabel.CENTER);
        btnStart.setHorizontalAlignment(JLabel.CENTER);
        btnStart.setBackground(new Color(0,0,153));
        btnStart.setForeground(new Color(255,255,0));
        btnStart.setFont(new Font("Cambria",3,34));
        btnStart.addActionListener(this);
        super.add(btnStart);
        
        btnSubmit=new JButton();
        btnSubmit.setText("SUBMIT");
        btnSubmit.setBounds(300, 500, 100, 30);
        btnSubmit.setBackground(new Color(47, 236, 239));
        btnSubmit.setVisible(false);
        btnSubmit.setFont(new Font("Cambria",3,18));
        btnSubmit.addActionListener(this);
        super.add(btnSubmit);
        
        btnExit=new JButton();
        btnExit.setText("EXIT");
        btnExit.setBounds(50, 500, 100, 30);
        btnExit.setBackground(new Color(47, 236, 239));
        btnExit.setFont(new Font("Cambria",3,18));
        btnExit.setVisible(false);
        btnExit.addActionListener(this);
        super.add(btnExit);
                
        lblQuesNo = new JLabel();
        lblQuesNo.setVerticalAlignment(JLabel.CENTER);
        lblQuesNo.setHorizontalAlignment(JLabel.CENTER);
        lblQuesNo.setVisible(false);
        lblQuesNo.setBackground(new Color(0,153,153));
        lblQuesNo.setOpaque(true);
        lblQuesNo.setBounds(100, 50, 500, 50);
        lblQuesNo.setFont(new Font("Courier New",3,20));
        super.add(lblQuesNo);
        
        
        lblA=makeLabel(250);
        lblB=makeLabel(300);
        lblC=makeLabel(350);
        lblD=makeLabel(400);
        
        btnA=makeButton("A",250);
        btnA.addActionListener(this);
        btnB=makeButton("B",300);
        btnB.addActionListener(this);
        btnC=makeButton("C",350);
        btnC.addActionListener(this);
        btnD=makeButton("D",400);
        btnD.addActionListener(this);
        
        txtQuestion = new JTextArea();
        txtQuestion.setBounds(100, 130, 500, 100);
        txtQuestion.setFont(new Font("Courier New",3,20));
        txtQuestion.setBackground(new Color(0,153,153));
        txtQuestion.setVisible(false);
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);
        txtQuestion.setEditable(false);
        super.add(txtQuestion);
        
        btnNext=new JButton();
        btnNext.setText("NEXT >");
        btnNext.setBounds(540, 500, 100, 30);
        btnNext.setVisible(false);
        btnNext.setFont(new Font("Cambria",3,18));
        btnNext.setBackground(new Color(47, 236, 239));
        btnNext.addActionListener(this);
        super.add(btnNext);
        
        try
        {
            Scanner sc = new Scanner(path);
            while(sc.hasNext())
            {
                String str=sc.nextLine();
                String s[]=str.split(",");
                Questions obj=new Questions(s[0],s[1],s[2],s[3],s[4],s[5]);
                questions.add(obj);
            }
            sc.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    private void nextQuestion(int i)
    {
        if(i<questions.size())
        {
            lblQuesNo.setText("Question Number : "+(i+1));
            txtQuestion.setText(questions.get(i).getQues());
            lblA.setText(questions.get(i).getOptionA());
            lblB.setText(questions.get(i).getOptionB());
            lblC.setText(questions.get(i).getOptionC());
            lblD.setText(questions.get(i).getOptionD());
            lblA.setBackground(new Color(0,153,153));
            lblB.setBackground(new Color(0,153,153));
            lblC.setBackground(new Color(0,153,153));
            lblD.setBackground(new Color(0,153,153));

        }
        else
        {
            end();
        }
    }
    
    public void end()
    {
        p.setBackground(new Color(0,0,153));
        lblQuesNo.setVisible(!(lblQuesNo.isVisible()));
        lblA.setVisible(!(lblA.isVisible()));
        lblB.setVisible(!(lblB.isVisible()));
        lblC.setVisible(!(lblC.isVisible()));
        lblD.setVisible(!(lblD.isVisible()));
        btnA.setVisible(!(btnA.isVisible()));
        btnB.setVisible(!(btnB.isVisible()));
        btnC.setVisible(!(btnC.isVisible()));
        btnD.setVisible(!(btnD.isVisible()));
        btnSubmit.setVisible(!(btnSubmit.isVisible()));
        btnNext.setVisible(!(btnNext.isVisible()));
        txtQuestion.setVisible(!(txtQuestion.isVisible()));
        lblEnd.setVisible(true);
        lblScore.setVisible(true);
        lblScore.setText(Integer.toString(score)+" / "+Integer.toString(questions.size()));
        btnExit.setBounds(300, 500, 100, 30);
    }
    
    public void correctAns(String s)
    {
        if(questions.get(i).getAns().equals(s))
            score++;
        if(questions.get(i).getAns().equals(lblA.getText()))
            lblA.setBackground(Color.green);
        else
            lblA.setBackground(Color.red);
        if(questions.get(i).getAns().equals(lblB.getText()))
            lblB.setBackground(Color.green);
        else
            lblB.setBackground(Color.red);
        if(questions.get(i).getAns().equals(lblC.getText()))
            lblC.setBackground(Color.green);
        else
            lblC.setBackground(Color.red);
        if(questions.get(i).getAns().equals(lblD.getText()))
            lblD.setBackground(Color.green);
        else
            lblD.setBackground(Color.red);
    }

    private void start()
    {
        p.setBackground(new Color(239,230,47));
        lblQuesNo.setVisible(!(lblQuesNo.isVisible()));
        lblA.setVisible(!(lblA.isVisible()));
        lblB.setVisible(!(lblB.isVisible()));
        lblC.setVisible(!(lblC.isVisible()));
        lblD.setVisible(!(lblD.isVisible()));
        lblWelcome.setVisible(!(lblWelcome.isVisible()));
        lblCaption.setVisible(!(lblCaption.isVisible()));
        btnA.setVisible(!(btnA.isVisible()));
        btnB.setVisible(!(btnB.isVisible()));
        btnC.setVisible(!(btnC.isVisible()));
        btnD.setVisible(!(btnD.isVisible()));
        btnNext.setVisible(!(btnNext.isVisible()));
        btnStart.setVisible(!(btnStart.isVisible()));
        btnSubmit.setVisible(!(btnSubmit.isVisible()));
        btnExit.setVisible(true);
        txtQuestion.setVisible(!(txtQuestion.isVisible()));
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object obj=e.getSource();
        if(obj==btnStart)
        {
            start();
            nextQuestion(0);
        }
        else if(obj==btnNext)
        {
            nextQuestion(++i);
            
        }
        else if(obj==btnA)
        {
            String s=lblA.getText();
            correctAns(s);
        }
        else if(obj==btnB)
        {
            String s=lblB.getText();
            correctAns(s);
        }
        else if(obj==btnC)
        {
            String s=lblC.getText();
            correctAns(s);
        }
        else if(obj==btnD)
        {
            String s=lblD.getText();
            correctAns(s);
        }
        else if(obj==btnSubmit)
        {
            end();
        }
        else if(obj==btnExit)
        {
            System.exit(0);
        }
    }
}
class MyFrame extends JFrame
{
    private MyPanel panel;
    
    public MyFrame()
    {
        panel = new MyPanel();
        panel.setBackground(new Color(153,51,255));
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}
public class MainClass {

    public static void main(String[] args) 
    {
        MyFrame f = new MyFrame();
        Toolkit tk=Toolkit.getDefaultToolkit();
        Image img=tk.getImage("img.jpg");
        f.setIconImage(img);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(700,600);
        f.setLocationRelativeTo(null);
        f.setTitle("Quiz Game");
        f.setFont(new Font("Courier New",3,20));
        f.setResizable(false);
    }
    
}