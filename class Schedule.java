class Schedule {
    private int id;
    private String className;
    private String trainer;
    private String date;
    private String startTime;
    private String endTime;
    private int capacity;

    public Schedule(int id, String className, String trainer, String date, String startTime, String endTime, int capacity) {
        this.id = id;
        this.className = className;
        this.trainer = trainer;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    // Getter dan setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getTrainer() { return trainer; }
    public void setTrainer(String trainer) { this.trainer = trainer; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    @Override
    public String toString() {
        return "ID: " + id + 
               "\nNama Kelas: " + className + 
               "\nPelatih: " + trainer + 
               "\nTanggal: " + date + 
               "\nWaktu: " + startTime + " - " + endTime + 
               "\nKapasitas: " + capacity;
    }
}
