interface IScheduleData {
    void saveSchedule(Schedule schedule);
    Schedule getSchedule(int scheduleId);
    void updateSchedule(Schedule schedule);
    void deleteSchedule(int scheduleId);
}