package com.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main extends JFrame implements ActionListener {

    JTabbedPane tabs = new JTabbedPane();   
    JPanel tabClock = new JPanel();
    JPanel tabTimer = new JPanel();
    JPanel tabWorldClock = new JPanel();
    JPanel tabAlarm = new JPanel();
    JPanel timerBtnPanel = new JPanel();

    Font font1 = new Font("Arial", Font.BOLD, 48); 
    Font font2 = new Font("Arial", Font.BOLD, 32); 

    JTextField nowTime = new JTextField(); 
    JTextField today = new JTextField(); 
    JTextField dayMonthAndYear = new JTextField(); 
    JTextField laps = new JTextField();
    JTextField watch = new JTextField();
    JTextField gmt=new JTextField(); 
    JTextField selectedTime=new JTextField(); 
    JTextField selectZone=new JTextField(); 

    JButton timerStart = new JButton(); 
    JButton timerLap = new JButton();
    JButton timerStop = new JButton(); 
    JButton timerRestart = new JButton(); 
  
    int elapsedTime = 0; // this value will increase one in every 1000 milli-seconds
    int sec = 0; // Store seconds
    int min = 0; // Store minutes
    int hour = 0; // Store hours
    int localTimeZonePlusHours;
    int localTimeZonePlusMinutes;
    // Strings
    String localTimeZone; // Store the local time zone - WorldClock
    String dayOfTheWeek = ""; // Store the day of the week - Clock
    String month = ""; // Store the month - Clock
    String localTime; // Store local time - WorldClock
    String seconds_str = String.format("%02d", sec);
    String minutes_str = String.format("%02d", min); 
    String hours_str = String.format("%02d", hour); 
    String lapsStr=""; 
    // SimpleDateFormat
    SimpleDateFormat timeForm; // Used to format time - Clock
    SimpleDateFormat pmOrAm; // Used find pm or am - Clock
    SimpleDateFormat todayForm; // day of week - Clock
    SimpleDateFormat monthForm; // month of the year - Clock
    SimpleDateFormat dayForm; // day of year - Clock
    SimpleDateFormat yearForm; // Year - Clock
    SimpleDateFormat GMT; // Used to get local time zone - WorldClock


    // Engine of the clock
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            hour = (elapsedTime / 3600000);
            min = (elapsedTime / 60000) % 60;
            sec = (elapsedTime / 1000) % 60;
            String seconds_str = String.format("%02d", sec);
            String minutes_str = String.format("%02d", min);
            String hours_str = String.format("%02d", hour);
            watch.setText(hours_str + " : " + minutes_str + " : " + seconds_str);
        }
    });



    Main() {
        // Window setups
        setBounds(200, 100, 500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setBackground(Color.DARK_GRAY);
        add(BorderLayout.CENTER, tabs);

        tabs.addTab("  Clock  ", tabClock());
        tabs.addTab("  Timer  ", tabTimer());
        tabs.addTab("  World Clock  ", tabWorldClock());
        
        setVisible(true);
        // starting clock
        updateTime();

    }

    public JPanel tabClock() {

        tabClock.setLayout(new GridLayout(3, 0));

        todayForm = new SimpleDateFormat("E");
        String ch = todayForm.format(Calendar.getInstance().getTime());
        switch (ch) {
            case "Mon":
                dayOfTheWeek = "MONDAY";
                break;
            case "Tue":
                dayOfTheWeek = "TUESDAY";
                break;
            case "Wed":
                dayOfTheWeek = "WEDNESDAY";
                break;
            case "Thu":
                dayOfTheWeek = "THURSDAY";
                break;
            case "Fri":
                dayOfTheWeek = "FRIDAY";
                break;
            case "Sat":
                dayOfTheWeek = "SATURDAY";
                break;
            case "Sun":
                dayOfTheWeek = "SUNDAY";
                break;
        }
        today.setText(dayOfTheWeek);

        dayForm = new SimpleDateFormat("d");
        yearForm = new SimpleDateFormat("y");
        monthForm = new SimpleDateFormat("M");

        ch = monthForm.format(Calendar.getInstance().getTime());
        switch (ch) {
            case "1":
                month = "JANUARY";
                break;
            case "2":
                month = "FEBRUARY";
                break;
            case "3":
                month = "MARCH";
                break;
            case "4":
                month = "APRIL";
                break;
            case "5":
                month = "MAY";
                break;
            case "6":
                month = "JUNE";
                break;
            case "7":
                month = "JULY";
                break;
            case "8":
                month = "AUGUST";
                break;
            case "9":
                month = "SEPTEMBER";
                break;
            case "10":
                month = "OCTOBER";
                break;
            case "11":
                month = "NOVEMBER";
                break;
            case "12":
                month = "DECEMBER";
                break;
        }
        dayMonthAndYear.setText(dayForm.format(Calendar.getInstance().getTime()) + " " + month + " " + yearForm.format(Calendar.getInstance().getTime()));


        nowTime.setBackground(Color.darkGray);
        today.setBackground(Color.darkGray);
        dayMonthAndYear.setBackground(Color.darkGray);

        nowTime.setHorizontalAlignment(JTextField.CENTER);
        nowTime.setForeground(Color.GREEN);
        today.setHorizontalAlignment(JTextField.CENTER);
        today.setForeground(Color.GREEN);
        dayMonthAndYear.setHorizontalAlignment(JTextField.CENTER);
        dayMonthAndYear.setForeground(Color.GREEN);

        nowTime.setFont(font1);
        today.setFont(font1);
        dayMonthAndYear.setFont(font1);


        nowTime.setFocusable(false);
        today.setFocusable(false);
        dayMonthAndYear.setFocusable(false);

        nowTime.setEditable(false);
        today.setEditable(false);
        dayMonthAndYear.setEditable(false);

        tabClock.add(nowTime, BorderLayout.CENTER);
        tabClock.add(today, BorderLayout.CENTER);
        tabClock.add(dayMonthAndYear, BorderLayout.CENTER);
        return tabClock;
    }

    public JPanel tabTimer() {

        tabTimer.setLayout(new GridLayout(3, 0));

        watch.setBackground(Color.darkGray);
        watch.setFocusable(false);
        watch.setEditable(false);
        watch.setForeground(Color.green);
        watch.setFont(font1);
        watch.setHorizontalAlignment(JTextField.CENTER);
        watch.setText(hours_str + " : " + minutes_str + " : " + seconds_str);

        laps.setBackground(Color.darkGray);
        laps.setForeground(Color.WHITE);
        laps.setFont(font1);
        laps.setHorizontalAlignment(JTextField.CENTER);
        laps.setFocusable(false);
        laps.setEditable(false);

        timerBtnPanel.setLayout(new GridLayout(0, 4));

        timerStart.setText("START");
        timerStart.setFocusable(false);
        timerStart.addActionListener(this);
        timerLap.setText("LAP");
        timerLap.setFocusable(false);
        timerLap.addActionListener(this);
        timerStop.setText("STOP");
        timerStop.setFocusable(false);
        timerStop.addActionListener(this);
        timerRestart.setText("RESTART");
        timerRestart.setFocusable(false);
        timerRestart.addActionListener(this);


        timerBtnPanel.add(timerStart);
        timerBtnPanel.add(timerLap);
        timerBtnPanel.add(timerStop);
        timerBtnPanel.add(timerRestart);

        tabTimer.add(watch, BorderLayout.CENTER);
        tabTimer.add(laps, BorderLayout.CENTER);
        tabTimer.add(timerBtnPanel);


        return tabTimer;

    }

    public JPanel tabWorldClock(){
        
        tabWorldClock.setLayout(new GridLayout(3,0));

        gmt.setBackground(Color.darkGray);
        gmt.setForeground(Color.green);
        gmt.setFont(font2);
        gmt.setHorizontalAlignment(JTextField.CENTER);
        GMT=new SimpleDateFormat("Z");
        localTimeZone=GMT.format(Calendar.getInstance().getTime());
        localTimeZonePlusHours= Integer.parseInt(localTimeZone.substring(0,3));
        localTimeZonePlusMinutes= Integer.parseInt(localTimeZone.substring(3));
        localTimeZone="+"+localTimeZonePlusHours+":"+localTimeZonePlusMinutes;
        gmt.setText("LOCAL TIME ZONE: "+localTimeZone);

        selectedTime.setBackground(Color.darkGray);
        selectedTime.setForeground(Color.green);
        selectedTime.setFont(font2);
        selectedTime.setHorizontalAlignment(JTextField.CENTER);

        //selectedTime.setText("GREENWICH TIME: ");

        selectZone.setBackground(Color.darkGray);
        selectZone.setForeground(Color.green);
        selectZone.setHorizontalAlignment(JTextField.CENTER);

        tabWorldClock.add(gmt,BorderLayout.CENTER);
        tabWorldClock.add(selectedTime,BorderLayout.CENTER);
        tabWorldClock.add(selectZone,BorderLayout.CENTER);

        return tabWorldClock;
    }

    public void timerStop() {
        timer.stop();
    }

    public void timerRunning() {
        timer.start();

    }

    public void timerLap() {
        laps.setText(lapsStr.concat(watch.getText()));
    }

    public void timerRestart() {
        elapsedTime = 0;
        sec = 0;
        min = 0;
        hour = 0;
    }

    public void updateTime() {
        while (true) {
            pmOrAm = new SimpleDateFormat("a");
            timeForm = new SimpleDateFormat("hh:mm:ss");
            if (pmOrAm.format(Calendar.getInstance().getTime()).equals("pm")) {
                nowTime.setText((timeForm.format(Calendar.getInstance().getTime())) + " PM");
            } else {
                nowTime.setText((timeForm.format(Calendar.getInstance().getTime())) + " AM");
            }
            localTime=timeForm.format(Calendar.getInstance().getTime());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timerStart) {
            timerRunning();
        }
        if (e.getSource() == timerStop) {
            timerStop();
        }
        if (e.getSource() == timerLap) {
            timerLap();
        }
        if (e.getSource() == timerRestart) {
            timerRestart();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
