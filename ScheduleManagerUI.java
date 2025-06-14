import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleManagerUI extends JFrame {
    private final JCalendar calendar;
    private final JTextArea scheduleArea;
    private final JButton addScheduleBtn;
    private final JButton viewSchedulesBtn;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final ScheduleManager scheduleManager;
    private final LectureManager lectureManager;

    public ScheduleManagerUI(LectureManager lectureManager, ScheduleManager scheduleManager) {
        this.scheduleManager = scheduleManager;
        this.lectureManager = lectureManager;

        setTitle("강의 일정 캘린더");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calendar = new JCalendar();

        scheduleArea = new JTextArea(10, 40);
        scheduleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(scheduleArea);

        addScheduleBtn = new JButton("일정 추가");
        viewSchedulesBtn = new JButton("일정 보기");

        addScheduleBtn.addActionListener(e -> {
            String date = dateFormat.format(calendar.getDate());
            String schedule = JOptionPane.showInputDialog("일정 내용을 입력하세요:");
            if (schedule != null && !schedule.trim().isEmpty()) {
                scheduleManager.addSchedule(date, schedule);
                JOptionPane.showMessageDialog(this, "일정이 추가되었습니다.");
            }
        });

        viewSchedulesBtn.addActionListener(e -> {
            String date = dateFormat.format(calendar.getDate());
            List<String> schedules = scheduleManager.getSchedulesForDate(date);
            scheduleArea.setText("");
            if (schedules.isEmpty()) {
                scheduleArea.append("해당 날짜에는 일정이 없습니다.\n");
            } else {
                for (String s : schedules) {
                    scheduleArea.append("- " + s + "\n");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(addScheduleBtn);
        panel.add(viewSchedulesBtn);

        setLayout(new BorderLayout());
        add(calendar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }
}
