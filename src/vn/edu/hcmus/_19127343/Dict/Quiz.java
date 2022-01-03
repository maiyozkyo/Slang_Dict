package vn.edu.hcmus._19127343.Dict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * vn.edu.hcmus._19127343.Dict
 * Created by Bleu
 * Date 1/3/2022 - 1:05 PM
 * Description: Source: (Youtuber) Bro Code
 */

public class Quiz extends Thread implements ActionListener {

    private static ArrayList<String> slangs = new ArrayList<>();
    private static ArrayList<String> means = new ArrayList<>();
    int index;
    int questionIndex = 0;
    int correct_guesses = 0;
    int seconds = 15;
    int total_questions = slangs.size();
    String answer = "";
    char trueAns;
    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if (seconds <= 0) {
                displayAnswer();
            }
        }
    });

    /**
     * Default Constructor with 2 array list slangSample as Questions And meanSample as Answers
     * @param slangSample
     * @param meansSample
     */
    public Quiz(ArrayList<String> slangSample, ArrayList<String> meansSample) {
        slangs = slangSample;
        means = meansSample;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0, 0, 650, 50);
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);

        textarea.setBounds(0, 50, 650, 50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25, 25, 25));
        textarea.setForeground(new Color(25, 255, 0));
        textarea.setFont(new Font("MV Boli", Font.BOLD, 25));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 100, 100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125, 100, 500, 100);
        answer_labelA.setBackground(new Color(50, 50, 50));
        answer_labelA.setForeground(new Color(25, 255, 0));
        answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelB.setBounds(125, 200, 500, 100);
        answer_labelB.setBackground(new Color(50, 50, 50));
        answer_labelB.setForeground(new Color(25, 255, 0));
        answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelC.setBounds(125, 300, 500, 100);
        answer_labelC.setBackground(new Color(50, 50, 50));
        answer_labelC.setForeground(new Color(25, 255, 0));
        answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelD.setBounds(125, 400, 500, 100);
        answer_labelD.setBackground(new Color(50, 50, 50));
        answer_labelD.setForeground(new Color(25, 255, 0));
        answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 35));

        seconds_left.setBounds(535, 510, 100, 100);
        seconds_left.setBackground(new Color(25, 25, 25));
        seconds_left.setForeground(new Color(255, 0, 0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535, 475, 100, 25);
        time_label.setBackground(new Color(50, 50, 50));
        time_label.setForeground(new Color(255, 0, 0));
        time_label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer >:D");

        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(25, 25, 25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);

        nextQuestion();
    }

    public void nextQuestion() {

        if (index >= total_questions) {
            frame.dispose();
        } else {
            textfield.setText("Question " + (questionIndex + 1));
            Random r = new Random();
            index = r.nextInt((slangs.size()-1 - 0) + 1 ) + 0;
            textarea.setText("What is the meaning of " + slangs.get(index));
            int randomAns = r.nextInt((means.size()-1 - 0) + 1 ) + 0;
            answer_labelA.setText(means.get(randomAns));
            randomAns = r.nextInt((means.size()-1 - 0) + 1 ) + 0;
            answer_labelB.setText(means.get(randomAns));
            randomAns = r.nextInt((means.size()-1 - 0) + 1 ) + 0;
            answer_labelC.setText(means.get(randomAns));
            randomAns = r.nextInt((means.size()-1 - 0) + 1 ) + 0;
            answer_labelD.setText(means.get(randomAns));
            int trueAns = r.nextInt((3 - 0) + 1 ) + 0;
            if (trueAns == 0) answer_labelA.setText(means.get(index));
            else if (trueAns == 1) answer_labelB.setText(means.get(index));
            else if (trueAns == 2) answer_labelC.setText(means.get(index));
            else answer_labelD.setText(means.get(index));
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        answer = means.get(index);
        if (e.getSource() == buttonA) {
            if (answer.equals(answer_labelA.getText())) {
                correct_guesses++;
                trueAns = 'A';
            }
        }
        if (e.getSource() == buttonB) {
            if (answer.equals(answer_labelB.getText())) {
                correct_guesses++;
                trueAns = 'B';
            }
        }
        if (e.getSource() == buttonC) {
            if (answer.equals(answer_labelC.getText())) {
                correct_guesses++;
                trueAns = 'C';
            }
        }
        if (e.getSource() == buttonD) {
            if (answer.equals(answer_labelD.getText())) {
                correct_guesses++;
                trueAns = 'D';
            }
        }
        displayAnswer();
    }

    public void displayAnswer() {
        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (trueAns != 'A')
            answer_labelA.setForeground(new Color(255, 0, 0));
        if (trueAns != 'B')
            answer_labelB.setForeground(new Color(255, 0, 0));
        if (trueAns != 'C')
            answer_labelC.setForeground(new Color(255, 0, 0));
        if (trueAns != 'D')
            answer_labelD.setForeground(new Color(255, 0, 0));

        Timer pause = new Timer(2000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                answer_labelA.setForeground(new Color(25, 255, 0));
                answer_labelB.setForeground(new Color(25, 255, 0));
                answer_labelC.setForeground(new Color(25, 255, 0));
                answer_labelD.setForeground(new Color(25, 255, 0));

                answer = "";
                seconds = 15;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }
}
