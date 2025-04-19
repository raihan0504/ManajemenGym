interface IMemberData {
    void saveMember(Member member);
    Member getMember(int memberId);
    void updateMember(Member member);
    void deleteMember(int memberId);
}