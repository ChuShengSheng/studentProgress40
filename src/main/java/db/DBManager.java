package db;

import entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBManager {


    public static List<Student> getAllActiveStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student where status = '1'");

            while (rs.next()) {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setSurname(rs.getString("surname"));
                st.setName(rs.getString("name"));
                st.setGroup(rs.getString("groupe"));
                st.setDate(rs.getDate("date"));
                students.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }


    public static List<Term> getAllActiveTerms() {
        ArrayList<Term> terms = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM term where status = '1'");

            while (rs.next()) {
               Term term = new Term();
               term.setId(rs.getInt("id"));
               term.setTerm(rs.getString("name_of_term"));
               term.setDuration(rs.getString("duration"));
               terms.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return terms;
    }


    public static List<Discipline> getAllActiveDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discipline where status = '1'");

            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return disciplines;
    }
    public static List<Mark> getMarksByStudentAndTerm(String idStudent, int
            idTerm) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d.id, d.discipline, m.mark FROM mark as m\n" +
                    "left join term_discipline as td on m.id_term_discipline = td.id\n" +
                    "left join discipline as d on td.id_discipline = d.id\n" +
                    "where m.id_student = "+idStudent+" and td.id_term = "+idTerm+"");

            while (rs.next()) {
               Mark mark = new Mark();
               mark.setMark(rs.getInt("mark"));

               Discipline discipline = new Discipline();
               discipline.setId(rs.getInt("id"));
               discipline.setDiscipline(rs.getString("discipline"));
               mark.setDiscipline(discipline);

               marks.add(mark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return marks;
    }

    public static void createStudent(String surname, String name, String groupe, String date) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `student` (`surname`, `name`, `groupe`, `date`) VALUES ('" + surname + "', '" + name + "', '" + groupe + "', '" + date + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createTerm(String duration, String [] disciplines) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT id, name_of_term FROM term order by id desc limit 1");

            String currentTerm = null;
            int idTerm = 0;

            while (resultSet.next()){
                currentTerm = resultSet.getString("name_of_term");
                idTerm = resultSet.getInt("id");
            }

            int termNumber = 0;
            termNumber = Integer.parseInt(currentTerm.substring(8));
            String term = "Семестр" + ( ++termNumber);
            stmt.execute("INSERT INTO `term` (`name_of_term`, `duration`) VALUES ('"+term+"', '"+duration+"');");

            int newId = ++idTerm;
            for(String discipline: disciplines){
                ResultSet resultSet1 = stmt.executeQuery("SELECT id FROM discipline\n" +
                        "where discipline = '"+discipline+"'");
                int idDiscipline = 0;
                while (resultSet1.next()){
                    idDiscipline = resultSet1.getInt("id");
                }

                stmt.execute("INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('"+newId+"', '"+idDiscipline+"');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createDiscipline(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `discipline` (`discipline`) VALUES ('"+name+"');");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteStudent(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = '"+id+"');");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteDiscipline(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `status` = '0' WHERE (`id` = '"+id+"');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteTerm(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `term` SET `status` = '0' WHERE (`id` = '"+id+"');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Student getStudentById(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student where status = '1' and id = " + id);

            while (rs.next()) {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setSurname(rs.getString("surname"));
                st.setName(rs.getString("name"));
                st.setGroup(rs.getString("groupe"));
                st.setDate(rs.getDate("date"));
                return st;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Term getTermById(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM term where status = '1' and id = " + id);

            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setDuration("duration");
                term.setTerm(rs.getString("name_of_term"));
                return term;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Discipline getDisciplineById(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discipline where status = '1' and id = " + id);

            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                return discipline;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void modifyStudent(String id, String surname, String name, String groupe, String dateToDB) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `surname` = '"+surname+"', `name` = '"+name+"', `groupe` = '"+groupe+"', `date` = '"+dateToDB+"' WHERE (`id` = '"+id+"');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyDiscipline(String id, String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `discipline` = '"+name+"' WHERE (`id` = '"+id+"');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyTerm(String id, String modifiedDuration, String[] modifiedDisciplineId) {
        int duration1 = 0;
        try {
           duration1 = Integer.parseInt(modifiedDuration.trim());
        }catch (NumberFormatException e){
            Pattern pattern = Pattern.compile("\\D");
            Matcher matcher = pattern.matcher(modifiedDuration);

            int i = 0;
            if (matcher.find()){
                i = matcher.start();
            }

            duration1 = Integer.parseInt(modifiedDuration.substring(0, i));
        }

        String wordWeek = null;
        String durationString = String.valueOf(duration1);
        if ((duration1 - 1)% 10 == 0 && duration1 != 11) {
            wordWeek = " неделя";
        } else  if ((durationString.substring(durationString.length() - 1).equals("2") ||
                durationString.substring(durationString.length() - 1).equals("3") ||
                durationString.substring(durationString.length() - 1).equals("4") &&
                duration1 != 12 && duration1 != 13 && duration1 != 14)){
            wordWeek = " недели";
        }else {
            wordWeek = " недель";
        }

        String durationResult = duration1 + wordWeek;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM `term_discipline` WHERE (`id_term` = '"+id+"')");
            if (modifiedDisciplineId != null){
                for (String newDisciplineId: modifiedDisciplineId){
                    stmt.execute("INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('"+id+"', '"+newDisciplineId+"')");
                }
            }

            stmt.execute("UPDATE `term` SET `duration` = '"+durationResult+"' WHERE (`id` = '"+id+"')");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean canLogin(String login, String password, String role) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_role as ur\n" +
                    "left join user as u on ur.id_user = u.id\n" +
                    "where ur.id_role = "+role+" and u.login = '"+login+"' and u.password = '"+password+"'");

            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<Discipline> getAllActiveDisciplinesByTerm(int idTerm) {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_40?user=root&password=S2023qL_n_1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d.id, d.discipline FROM term_discipline as td\n" +
                    "left join term as t on td.id_term = t.id\n" +
                    "left join discipline as d on td.id_discipline = d.id\n" +
                    "where td.id_term = '"+idTerm+"' and d.status = '1'\n");

            while (rs.next()) {
               Discipline discipline= new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return disciplines;
    }


}
