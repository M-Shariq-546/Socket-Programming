<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Drink Selection</title>
</head>
<body>
    <form action="processSelection.jsp" method="post">
        <label for="drink">Select a Drink:</label>
        <select name="drink" id="drink">
            <option value="" disabled selected>Select a drink</option>

            <% 
            // Java code to retrieve data from the database and populate the dropdown
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "root", "");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name, rate FROM beverages");

                while (rs.next()) {
                    String drinkName = rs.getString("name");
                    int drinkRate = rs.getInt("rate");
            %>
                <option value="<%=drinkRate%>"><%=drinkName%> - $<%=drinkRate%></option>
            <%
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            %>
        </select>
        <br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
