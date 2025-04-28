class Teacher:
    def __init__(self, name, username, password):
        self.name = name
        self.username = username
        self.password = password
        self.is_logged_in = False

    def get_name(self):
        return self.name

    def get_username(self):
        return self.username

    def get_password(self):
        return self.password

    def set_username(self, username):
        self.username = username
        print("Username updated successfully")

    def set_password(self, password):
        self.password = password
        print("Password updated successfully")

    def set_logged_in(self, is_logged_in):
        self.is_logged_in = is_logged_in

    def is_logged_in_func(self):
        return self.is_logged_in

    def login(self, username, password):
        return self.username == username and self.password == password

    def logout(self):
        self.is_logged_in = False
        print("Logged out successfully")


class Student:
    def __init__(self, name, reg_no, age, marks):
        self.name = name
        self.reg_no = reg_no
        self.age = age
        self.marks = marks

    def get_name(self):
        return self.name

    def get_reg_no(self):
        return self.reg_no

    def get_age(self):
        return self.age

    def get_marks(self):
        return self.marks

    def set_marks(self, marks):
        self.marks = marks
        print("Marks updated successfully")

    def display_details(self):
        print(f"Name: {self.name}, Reg No: {self.reg_no}, Age: {self.age}, Marks: {self.marks}")


class SManagement:
    students = []
    teachers = []
    current_teacher = None

    @staticmethod
    def register():
        name = input("Enter the name of the teacher - ")
        username = input("Enter the username - ")
        password = input("Enter the password - ")
        SManagement.teachers.append(Teacher(name, username, password))
        print("Teacher registered successfully")

    @staticmethod
    def login():
        username = input("Enter the username - ")
        password = input("Enter password - ")
        for teacher in SManagement.teachers:
            if teacher.login(username, password):
                print("Login successful")
                teacher.set_logged_in(True)
                SManagement.current_teacher = teacher
                return
        print("Invalid username or password")

    @staticmethod
    def add_student():
        if SManagement.current_teacher is None or not SManagement.current_teacher.is_logged_in_func():
            print("Please login first")
            return

        name = input("Enter the name of the student - ")
        reg_no = int(input("Enter the registration number - "))
        age = int(input("Enter the age - "))
        marks = float(input("Enter the marks - "))
        SManagement.students.append(Student(name, reg_no, age, marks))
        print("Student added successfully")

    @staticmethod
    def view_student():
        if SManagement.current_teacher is None or not SManagement.current_teacher.is_logged_in_func():
            print("Please login first")
            return

        reg_no = int(input("Enter the registration number - "))
        found = False
        for student in SManagement.students:
            if student.get_reg_no() == reg_no:
                student.display_details()
                found = True
                break
        if not found:
            print("Student not found")

    @staticmethod
    def update_marks():
        if SManagement.current_teacher is None or not SManagement.current_teacher.is_logged_in_func():
            print("Please login first")
            return

        reg_no = int(input("Enter the registration number - "))
        found = False
        for student in SManagement.students:
            if student.get_reg_no() == reg_no:
                marks = float(input("Enter new marks - "))
                student.set_marks(marks)
                found = True
                break
        if not found:
            print("Student not found")

    @staticmethod
    def delete_student():
        if SManagement.current_teacher is None or not SManagement.current_teacher.is_logged_in_func():
            print("Please login first")
            return

        reg_no = int(input("Enter the registration number - "))
        found = False
        for student in SManagement.students:
            if student.get_reg_no() == reg_no:
                SManagement.students.remove(student)
                print("Student deleted successfully")
                found = True
                break
        if not found:
            print("Student not found")

    @staticmethod
    def logout():
        if SManagement.current_teacher is not None:
            SManagement.current_teacher.logout()
            SManagement.current_teacher = None
        else:
            print("No teacher is currently logged in")
    @staticmethod
    def main():
        while True:
            print("\n=== Student Management System ===")
            print("1. Login as Teacher")
            print("2. Register New Teacher")
            print("3. Add Student")
            print("4. View Student")
            print("5. Update Marks")
            print("6. Delete Student")
            print("7. Logout")
            print("8. Exit")
            choice = int(input("Enter your choice: "))
            if choice == 1:
                SManagement.login()
            elif choice == 2:
                SManagement.register()
            elif choice == 3:
                SManagement.add_student()
            elif choice == 4:
                SManagement.view_student()
            elif choice == 5:
                SManagement.update_marks()
            elif choice == 6:
                SManagement.delete_student()
            elif choice == 7:
                SManagement.logout()
            elif choice == 8:
                print("Exiting the program")
                break
            else:
                print("Invalid choice")
if __name__ == "__main__":
    SManagement.main()
