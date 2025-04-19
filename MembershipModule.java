class MembershipModule {
    private IMemberData memberData;
    private java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static int nextMemberId = 1;
    
    public MembershipModule(IMemberData memberData) {
        this.memberData = memberData;
    }
    
    public void showMenu() {
        while (true) {
            System.out.println("\n===== MENU KEANGGOTAAN =====");
            System.out.println("1. Tambah Anggota Baru");
            System.out.println("2. Lihat Detail Anggota");
            System.out.println("3. Perbarui Data Anggota");
            System.out.println("4. Hapus Anggota");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Menghabiskan newline
            
            switch (choice) {
                case 1:
                    addNewMember();
                    break;
                case 2:
                    viewMemberDetails();
                    break;
                case 3:
                    updateMemberDetails();
                    break;
                case 4:
                    deleteMember();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    private void addNewMember() {
        System.out.println("\n--- TAMBAH ANGGOTA BARU ---");
        
        int id = nextMemberId++;
        
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telepon: ");
        String phone = scanner.nextLine();
        
        System.out.print("Tipe Keanggotaan (Reguler/Premium/VIP): ");
        String membershipType = scanner.nextLine();
        
        System.out.print("Tanggal Kadaluarsa (DD/MM/YYYY): ");
        String expiryDate = scanner.nextLine();
        
        Member newMember = new Member(id, name, email, phone, membershipType, expiryDate);
        memberData.saveMember(newMember);
        
        System.out.println("Anggota baru berhasil ditambahkan dengan ID: " + id);
    }
    
    private void viewMemberDetails() {
        System.out.print("\nMasukkan ID Anggota: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Member member = memberData.getMember(id);
        if (member != null) {
            System.out.println("\n--- DETAIL ANGGOTA ---");
            System.out.println(member);
        } else {
            System.out.println("Anggota dengan ID " + id + " tidak ditemukan.");
        }
    }
    
    private void updateMemberDetails() {
        System.out.print("\nMasukkan ID Anggota yang akan diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Member member = memberData.getMember(id);
        if (member != null) {
            System.out.println("\n--- PERBARUI DATA ANGGOTA ---");
            System.out.println("Data saat ini:");
            System.out.println(member);
            
            System.out.print("\nNama baru (kosongkan jika tidak berubah): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                member.setName(name);
            }
            
            System.out.print("Email baru (kosongkan jika tidak berubah): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                member.setEmail(email);
            }
            
            System.out.print("Telepon baru (kosongkan jika tidak berubah): ");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) {
                member.setPhone(phone);
            }
            
            System.out.print("Tipe Keanggotaan baru (kosongkan jika tidak berubah): ");
            String membershipType = scanner.nextLine();
            if (!membershipType.isEmpty()) {
                member.setMembershipType(membershipType);
            }
            
            System.out.print("Tanggal Kadaluarsa baru (kosongkan jika tidak berubah): ");
            String expiryDate = scanner.nextLine();
            if (!expiryDate.isEmpty()) {
                member.setExpiryDate(expiryDate);
            }
            
            memberData.updateMember(member);
            System.out.println("Data anggota berhasil diperbarui.");
        } else {
            System.out.println("Anggota dengan ID " + id + " tidak ditemukan.");
        }
    }
    
    private void deleteMember() {
        System.out.print("\nMasukkan ID Anggota yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Member member = memberData.getMember(id);
        if (member != null) {
            System.out.print("Anda yakin ingin menghapus anggota " + member.getName() + "? (y/n): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("y")) {
                memberData.deleteMember(id);
                System.out.println("Anggota berhasil dihapus.");
            } else {
                System.out.println("Penghapusan dibatalkan.");
            }
        } else {
            System.out.println("Anggota dengan ID " + id + " tidak ditemukan.");
        }
    }
}