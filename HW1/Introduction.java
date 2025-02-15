public class Introduction {

    public static void main(String[] args) {
        String hoTen = "Pham Cong Thanh";
        String maSinhVien = "24022455";
        String lop = "INT2204 1";
        String githubUsername = "taintedsilk";
        String email = "24022455@vnu.edu.vn";

        System.out.println(hoTen + "\t" + maSinhVien + "\t" + lop + "\t" + githubUsername + "\t" + email);

        for (int i = 10; i > 0; i--) {
            String bottle = (i -1) == 1 ? "bottle" : "bottles";
            System.out.println( (i-1 > 0 ? (i - 1) + " " + bottle + " of beer on the wall, " + (i-1) + " " + bottle + " of beer.\nTake one down, pass it around," : "No more bottles of beer on the wall."));

        }
    }
}