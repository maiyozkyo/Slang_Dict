package vn.edu.hcmus._19127343.Dict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        createAndShowGUI();
    }
    private static int exLen;
    private static ArrayList<String> slangs = new ArrayList<>();
    private static ArrayList<String> means = new ArrayList<>();
    private static ArrayList<String> slangsHis = new ArrayList<>();
    private static ArrayList<String> defsHis = new ArrayList<>();

    private static void start() throws FileNotFoundException {
        File currentSlang = new File("current_slang.txt");
        Scanner sc = new Scanner(currentSlang);
        sc.nextLine();
        while (sc.hasNextLine()){
            String word = sc.nextLine();
            String[] split = word.split("`");
            if (split.length == 1)
                System.out.println(word);
            else {
                slangs.add(split[0]);
                means.add(split[1]);
            }
        }
        exLen = slangs.size();
        File slangHis = new File("hslangs.txt");
        File defHis = new File("hdefs.txt");
        Scanner scan_Slang = new Scanner(slangHis);
        Scanner scan_Def = new Scanner(defHis);
        while (scan_Slang.hasNextLine()){
            String sword = scan_Slang.nextLine();
            slangsHis.add(sword);
        }
        while (scan_Def.hasNextLine()){
            String dword = scan_Def.nextLine();
            defsHis.add(dword);
        }
        sc.close();
        scan_Slang.close();
        scan_Def.close();
    }

    public static void addComp(Container fullpane) throws FileNotFoundException {
        Container pane = new Container();
        pane.setLayout(
                new BoxLayout(pane, 1)
        );
        start();
        //define
        JPanel for_slang = new JPanel();
        JPanel for_def = new JPanel();
        JPanel null_pan1 = new JPanel();
        JPanel null_pan2 = new JPanel();
        JPanel null_pan3 = new JPanel();
        JPanel for_search = new JPanel();
        JPanel for_btn = new JPanel();
        JTextArea history = new JTextArea();
        history.setEditable(false);

        //set layout for panel
        for_slang.setLayout(new BorderLayout());
        for_def.setLayout(new BorderLayout());
        null_pan1.setLayout(new BorderLayout());
        null_pan2.setLayout(new BorderLayout());
        null_pan3.setLayout(new BorderLayout());
        for_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        for_btn.setLayout(new FlowLayout(FlowLayout.CENTER));


        //Set PreferredSize for slang panel
        for_slang.setPreferredSize(new Dimension(700, 3));
        null_pan1.setPreferredSize(new Dimension(700, 3));

        //Add component to slang panel
        JLabel lSlang = new JLabel("Input slang word here");
        lSlang.setHorizontalAlignment(JLabel.CENTER);
        for_slang.add(lSlang, BorderLayout.PAGE_START);
        JButton bSlangQuest = new JButton("Slang Question");
        bSlangQuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame sQuestionWindow = new JFrame("Funny Slang Question");
                sQuestionWindow.getContentPane().setLayout(new BoxLayout(sQuestionWindow.getContentPane(), 1));
                sQuestionWindow.setSize(500, 500);
                sQuestionWindow.setVisible(true);

                final int[] Score = {0};
                Container pQuestion = new Container();
                pQuestion.setSize(new Dimension(250,10));
                pQuestion.setLayout(new BoxLayout(pQuestion, 0));
                JTextField tQuestion = new JTextField(30);
                tQuestion.setHorizontalAlignment(0);
                tQuestion.setEditable(false);
                pQuestion.add(tQuestion);
                JTextField tScore = new JTextField(3);
                tScore.setEditable(false);
                Random r = new Random();
                pQuestion.add(tScore);
                JTextField tTimer = new JTextField(3);
                tTimer.setEditable(false);
                tTimer.setHorizontalAlignment(0);
                JPanel null_Layout = new JPanel();
                null_Layout.setLayout(new FlowLayout());
                null_Layout.setPreferredSize(new Dimension(350, 350));

                Container pBtn = new Container();
                pBtn.setLayout(new GridLayout(2,2));
                JButton[] btns = new JButton[4];
                btns[0] = new JButton();
                btns[1] = new JButton();
                btns[2] = new JButton();
                btns[3] = new JButton();

                pBtn.add(btns[0]);
                pBtn.add(btns[1]);
                pBtn.add(btns[2]);
                pBtn.add(btns[3]);
                sQuestionWindow.getContentPane().add(pQuestion);
                sQuestionWindow.getContentPane().add(null_Layout);
                sQuestionWindow.getContentPane().add(pBtn);

                while(true){
                    int index_question = r.nextInt((slangs.size()-1 - 0) + 1 ) + 0;
                    tQuestion.setText("What is/are the meaning of " + slangs.get(index_question));
                    tScore.setHorizontalAlignment(0);
                    tScore.setText(String.valueOf(Score[0]));
                    int time = 20;
                    tTimer.setText(String.valueOf(time) + " s");
                    pQuestion.add(tTimer);

                    int correct = r.nextInt((3 - 0) + 1 ) + 0;
                    btns[correct].setText(means.get(index_question).replace(",", " |"));
                    int random = 0;
                    for (int i = 0; i < 3; i++){
                        int finalI = i;
                        btns[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (finalI == correct)
                                    Score[0]++;

                            }
                        });
                        if (i != correct){
                            while (random == index_question){
                                random = r.nextInt((slangs.size()-1 - 0) + 1 ) + 0;
                            }
                            btns[i].setText(means.get(random).replace(",", " |"));
                        }
                    }
                }
            }
        });
        bSlangQuest.setPreferredSize(new Dimension(200, 3));
        for_slang.add(bSlangQuest, BorderLayout.LINE_START);
        JTextField tSlang = new JTextField(30);
        tSlang.setHorizontalAlignment(0);
        tSlang.setPreferredSize(new Dimension(400, 3));
        for_slang.add(tSlang, BorderLayout.CENTER);
        JButton bSlangHis = new JButton("Slang History");
        bSlangHis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String history_search = "";
                for (int i = 0; i < slangsHis.size(); i++){
                    history_search += "\t" + slangsHis.get(i) + "\n";
                }
                history.setText(history_search);
            }
        });
        bSlangHis.setPreferredSize(new Dimension(130, 3));
        for_slang.add(bSlangHis, BorderLayout.LINE_END);

        //Set PreferredSize for def panel
        for_def.setPreferredSize(new Dimension(700, 3));
        null_pan2.setPreferredSize(new Dimension(700, 3));

        //Add component to def panel
        JLabel lDef = new JLabel("Input definition here");
        lDef.setHorizontalAlignment(JLabel.CENTER);
        for_def.add(lDef, BorderLayout.PAGE_START);
        JButton bDefQuest = new JButton("Definition Question");
        bDefQuest.setPreferredSize(new Dimension(200, 3));
        for_def.add(bDefQuest, BorderLayout.LINE_START);
        JTextField tDef = new JTextField(30);
        tDef.setHorizontalAlignment(0);
        tDef.setPreferredSize(new Dimension(400, 3));
        for_def.add(tDef, BorderLayout.CENTER);
        JButton bDefHis = new JButton("Definition History");
        bDefHis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String history_search = "";
                for (int i = 0; i < defsHis.size(); i++){
                    history_search += "\t" + defsHis.get(i) + "\n";
                }
                history.setText(history_search);
            }
        });
        bDefHis.setPreferredSize(new Dimension(150, 3));
        for_def.add(bDefHis, BorderLayout.LINE_END);

        //Add component to search panel
        JLabel lRes = new JLabel("Your result here");
        for_search.add(lRes);
        JTextArea tRes = new JTextArea(5, 50);
        tRes.setLineWrap(true);
        tRes.setEditable(false);
        tRes.setAlignmentX(0);
        for_search.add(tRes);
        JButton bSearch = new JButton("Search");
        bSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchWord = tSlang.getText();
                String allMean = "";
                if (!searchWord.equals("")){
                    allMean = means.get(slangs.indexOf(searchWord));
                    allMean = allMean.replace("|", ",");
                    slangsHis.add(0, searchWord);
                    if (slangsHis.size() > 20){
                        slangsHis.remove(20);
                    }

                }
                else {
                    searchWord = tDef.getText();
                    if (!searchWord.equals(" ") && !searchWord.equals("")){
                        for (int i = 0; i < means.size(); i++){
                            if (means.get(i).contains(searchWord)){
                                allMean += slangs.get(i) + ", ";
                            }
                        }
                        allMean = allMean.substring(0, allMean.length()-1);
                        defsHis.add(0, searchWord);
                        if (defsHis.size() > 20){
                            defsHis.remove(20);
                        }
                    }
                }
                tDef.setText("");
                tSlang.setText("");
                tRes.setText(allMean);
            }
        });
        bSearch.setPreferredSize(new Dimension(100, 40));
        for_search.add(bSearch);

        //Add component to button panel
        JButton bAdd = new JButton("Add new slang word");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addWindow = new JFrame("Add new Slang word");
                addWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                addWindow.getContentPane().setLayout(
                        new BoxLayout(addWindow.getContentPane(), 1)
                );
                Container InputPanel = new Container();
                InputPanel.setLayout(new BoxLayout(InputPanel, 0));
                Container SW = new Container();
                SW.setLayout(new BoxLayout(SW, 1));
                SW.setPreferredSize(new Dimension(50, 30));
                JLabel lNewSlangWord = new JLabel("New slang word");
                lNewSlangWord.setHorizontalAlignment(JLabel.CENTER);
                SW.add(lNewSlangWord);
                JTextField tNewSlangWord = new JTextField(13);
                tNewSlangWord.setHorizontalAlignment(0);
                SW.add(tNewSlangWord);
                InputPanel.add(SW);

                Container DW = new Container();
                DW.setLayout(new BoxLayout(DW, 1));
                DW.setPreferredSize(new Dimension(50, 30));
                JLabel lNewDefWord = new JLabel("Definition");
                lNewDefWord.setHorizontalAlignment(JLabel.CENTER);
                DW.add(lNewDefWord);
                JTextField tNewDefWord = new JTextField(13);
                tNewDefWord.setHorizontalAlignment(0);
                DW.add(tNewDefWord);

                JPanel nullPanel = new JPanel();
                nullPanel.setPreferredSize(new Dimension(30, 20));
                InputPanel.add(nullPanel);
                InputPanel.add(DW);

                Container btnPanel = new Container();
                btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton addOk = new JButton("OK");
                addOk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!tNewSlangWord.getText().equals("") && !tNewSlangWord.getText().equals(" ")){
                            if (slangs.indexOf(tNewSlangWord.getText()) < 0) {
                                slangs.add(tNewSlangWord.getText());
                                means.add(tNewDefWord.getText().replace(",", " |"));
                                addWindow.dispose();
                            }
                            else {
                                JFrame notify = new JFrame("Warning");
                                notify.getContentPane().setLayout(
                                        new FlowLayout(FlowLayout.CENTER)
                                );
                                JLabel warning = new JLabel("This slang word is already exist");
                                warning.setHorizontalAlignment(JLabel.CENTER);
                                notify.getContentPane().add(warning);

                                JPanel btn = new JPanel();
                                btn.setLayout(new FlowLayout(FlowLayout.CENTER));
                                JButton OverWrite = new JButton("Overwrite");
                                OverWrite.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String temp = tNewDefWord.getText();
                                        means.set(slangs.indexOf(tNewSlangWord.getText()), temp.replace(",", " |"));
                                        notify.dispose();
                                        addWindow.dispose();
                                    }
                                });
                                JButton Dup = new JButton("Duplicate");
                                Dup.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String temp = tNewDefWord.getText();
                                        slangs.add(tNewSlangWord.getText());
                                        means.add(tNewSlangWord.getText().replace(",", " |"));
                                        notify.dispose();
                                        addWindow.dispose();
                                    }
                                });
                                JButton Cancel = new JButton("Cancel");
                                Cancel.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        notify.dispose();
                                        addWindow.dispose();
                                    }
                                });
                                btn.add(OverWrite);
                                btn.add(Dup);
                                btn.add(Cancel);

                                notify.getContentPane().add(btn);
                                notify.setSize(300,100);
                                notify.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                notify.setVisible(true);
                            }
                        }
                    }
                });
                btnPanel.add(addOk);
                JButton addCan = new JButton("Cancel");
                addCan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addWindow.dispose();
                    }
                });
                btnPanel.add(addCan);

                addWindow.getContentPane().add(InputPanel);
                addWindow.getContentPane().add(btnPanel);
                addWindow.setSize(500,400);
                addWindow.setVisible(true);
            }
        });
        for_btn.add(bAdd);
        JButton bRandom = new JButton("Random Slang word");
        bRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                int index = r.nextInt((slangs.size()-1 - 0) + 1 ) + 0;
                tSlang.setText(slangs.get(index));
                tRes.setText(means.get(index));
            }
        });
        for_btn.add(bRandom);

        JButton bEdit = new JButton("Edit");
        bEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame editWindow = new JFrame();
                editWindow.getContentPane().setLayout(
                        new BoxLayout(editWindow.getContentPane(), 1)
                );

                Container oldpane = new Container();
                oldpane.setLayout(new FlowLayout(FlowLayout.CENTER));
                JLabel loldSlang = new JLabel("Old Slang Word");
                JTextField tOldSlang = new JTextField(15);
                tOldSlang.setText(tSlang.getText());
                oldpane.add(loldSlang);
                oldpane.add(tOldSlang);

                Container newpane = new Container();
                newpane.setLayout(new FlowLayout(FlowLayout.CENTER));
                JLabel lnewSlang = new JLabel("New Slang Word");
                newpane.add(lnewSlang);
                JTextField tNewSlang = new JTextField(15);
                newpane.add(tNewSlang);

                Container btnPane = new Container();
                btnPane.setLayout(new FlowLayout(1));
                JButton eOk = new JButton("OK");
                eOk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!tNewSlang.getText().equals(" ") && !tNewSlang.getText().equals("") && !tOldSlang.getText().equals("") && !tOldSlang.getText().equals(" ")){
                            slangs.set(slangs.indexOf(tOldSlang.getText()), tNewSlang.getText());
                            editWindow.dispose();
                            tSlang.setText(tNewSlang.getText());
                        }
                    }
                });
                JButton eCan = new JButton("Cancel");
                eCan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editWindow.dispose();
                    }
                });
                btnPane.add(eOk);
                btnPane.add(eCan);

                editWindow.getContentPane().add(oldpane);
                editWindow.getContentPane().add(newpane);
                editWindow.getContentPane().add(btnPane);
                editWindow.setSize(300, 300);
                editWindow.setVisible(true);
            }
        });
        for_btn.add(bEdit);
        JButton bDel = new JButton("Delete");
        bDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tSlang.getText().equals("") && !tSlang.getText().equals(" ")){
                    if (!tRes.getText().equals("") && !tRes.getText().equals(" ")){
                        int index = slangs.indexOf(tSlang.getText());
                        if (index > -1) {
                            tRes.setText("Removed Slang Word: " + tSlang.getText());
                            slangs.remove(index);
                            means.remove(index);
                        }
                        tSlang.setText("");
                    }
                }
            }
        });
        for_btn.add(bDel);

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File originSlang = new File("slang.txt");
                Scanner sc = null;
                try {
                    sc = new Scanner(originSlang);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                sc.nextLine();
                while (sc.hasNextLine()){
                    String word = sc.nextLine();
                    String[] split = word.split("`");
                    if (split.length == 1)
                        System.out.println(word);
                    else {
                        slangs.add(split[0]);
                        means.add(split[1]);
                    }
                }
                sc.close();
                tRes.setText("Reset to original file: Success");
            }
        });
        for_btn.add(reset);
        //Add to main layout
        pane.add(for_slang);
        pane.add(null_pan1);
        pane.add(for_def);
        pane.add(null_pan2);
        pane.add(for_search);
        pane.add(for_btn);
        pane.add(null_pan3);

        fullpane.add(pane);
        fullpane.add(history);
    }
    private static void createAndShowGUI() throws FileNotFoundException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame window = new JFrame("Slang - Definition Dictionary");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    FileWriter hDef = new FileWriter("hdefs.txt");
                    FileWriter hSlang = new FileWriter("hslangs.txt");
                    for (int i = 0; i < defsHis.size(); i++){
                        hDef.write(defsHis.get(i) + "\n");
                    }
                    for (int i = 0; i < slangsHis.size(); i++){
                        hSlang.write(slangsHis.get(i) + "\n");
                    }
                    hDef.close();
                    hSlang.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (exLen != slangs.size()){
                    try {
                        FileWriter current_Slang = new FileWriter("current_slang.txt");
                        for (int i = 0; i < slangs.size(); i++){
                            String data = slangs.get(i) + "`" + means.get(i) + "\n";
                            current_Slang.write(data);
                        }
                        current_Slang.close();
                        System.out.println("Updated");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Shutdown-thread"));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(
                new BoxLayout(window.getContentPane(), 0)
        );
        addComp(window);
        window.setSize(1000,500);
        window.setVisible(true);
    }
}
