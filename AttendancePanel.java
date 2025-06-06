// package Attend2;

import javax.swing.*;
import java.awt.*;


class AttendancePanel extends JPanel {

    public AttendancePanel(String lectureName, AttendanceManager attManager) {
        setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel("출석 상태를 선택하세요:", JLabel.CENTER);
        add(label);

        JPanel btnPanel = new JPanel(new FlowLayout());

        JButton attendBtn = new JButton("출석");
        JButton absentBtn = new JButton("결석");
        JButton lateBtn = new JButton("지각");

        attendBtn.addActionListener(e -> {
            String today = java.time.LocalDate.now().toString();
            attManager.addRecord(new AttendanceRecord(lectureName, today, "출석"));
            JOptionPane.showMessageDialog(this, "출석 처리 완료!");
            // 다이얼로그가 아니므로 dispose() 대신 적절한 UI 업데이트 로직 필요
        });

        absentBtn.addActionListener(e -> {
            String today = java.time.LocalDate.now().toString();
            attManager.addRecord(new AttendanceRecord(lectureName, today, "결석"));
            JOptionPane.showMessageDialog(this, "결석 처리 완료!");
        });

        lateBtn.addActionListener(e -> {
            String today = java.time.LocalDate.now().toString();
            attManager.addRecord(new AttendanceRecord(lectureName, today, "지각"));
            JOptionPane.showMessageDialog(this, "지각 처리 완료!");
        });

        btnPanel.add(attendBtn);
        btnPanel.add(absentBtn);
        btnPanel.add(lateBtn);
        add(btnPanel);
    }
}


