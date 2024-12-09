# LAB WEEK 05: Ứng dụng Web sử dụng Spring Boot tạo mô hình tuyển dụng việc làm


## Thông tin sinh viên thực hiện
- **Họ và tên:** Đổng Mạnh Dũng
- **MSSV:** 21099401
- **Lớp:** DHKTPM17C - K17 - Khoa Công Nghệ Thông Tin
- **Môn học**: Lập trình WWW - Java
- **Giảng viên**: TS Võ Văn Hải x Ths Đặng Thị Thu Hà


[Link Github Here: ](https://github.com/DongManhDung/www_lab_week5)

## I. Mô tả bài toán

### 1. Sơ đồ mô hình
![Lab Diagram](https://i.ibb.co/W2LnVbV/Diagram-lab-05.png)
- Mô hình này có phân chia vai trò (Role): Bao gồm Company (Công ty) và Candidate (Ứng viên).

### 2. Đặc tả sơ lược mô hình ứng tuyển công việc giữa Company và Candidate
**- Company:**
  - Đăng nhập bằng email company của mình, mỗi company chỉ chứa 1 email duy nhất.
  - Xem anh sách các công việc tương ứng.
  - Đăng tin tuyển dụng dựa vào jobName, description, skillName, skillDescription, skill Type, skill Level.
  - Tìm kiếm các ứng viên phù hợp dựa vào jobName, skillName, skillType, skillLevel.
  - Thống kê số lượng ứng viên phù hợp sau khi tìm kiếm.
  - Gửi mail cho candidate đã chọn.

**- Candidate:**
  - Đăng nhập bằng email cá nhân, mỗi candidate chỉ chứa 1 email duy nhất.
  - Xem danh sách các công việc tuyển dụng phù hợp.
  - Nhận được thư mời từ company nếu trúng tuyển.
  - Xem hộp thư email.

### 3. Công nghệ sử dụng
  - **Backend**: Spring boot, Spring Data JPA, Spring Mail, Hibernate & JPA.
  - **Frontend**: Thymeleaf, Bootstrap, HTML, CSS, JavaScript.
  - **IDE**: IntelliJ IDEA.
  - **Database**: MariaDB.
  - **Version Control**: Git, Github.
  - **Build Tool**: Gradle.
  - **Server**: Apache Tomcat.
  - **Data Mining**: Weka.
  - **Email Server**: Gmail.

### 4. Các Entities trong hệ thống
- Đã hoàn thành:
  - **Company**: Bao gồm thông tin về công ty, như tên, địa chỉ, email, số điện thoại, và trang web.
  - **Address**: Mô tả địa chỉ của công ty và ứng viên.
  - **Job**: Thông tin về công việc của công ty, như mô tả công việc và tên công việc.
  - **Skill**: Các kỹ năng cần thiết cho công việc.
  - **Candidate**: Thông tin ứng viên, bao gồm tên, email, ngày sinh, địa chỉ, và thông tin liên quan.
  - **Job_Skill**: Liên kết giữa công việc và các kỹ năng yêu cầu cho công việc đó.
  - **Candidate_Skill**: Liên kết giữa ứng viên và các kỹ năng mà họ sở hữu.
  - Ngoài ra còn thêm: Email đẻ lưu trữ nội dung email phục vụ công việc ứng tuyển.
- Chưa hoàn thành:
  - **Experience**: Các kinh nghiệm làm việc của ứng viên.

### 4. Các Repository Interface trong hệ thống
- Đã hoàn thành:
  - **CompanyRepository**: Interface để thao tác với bảng **company**.
  - **AddressRepository**: Interface để thao tác với bảng **address**.
  - **JobRepository**: Interface để thao tác với bảng **job**.
  - **SkillRepository**: Interface để thao tác với bảng **skill**.
  - **CandidateRepository**: Interface để thao tác với bảng **candidate**.
  - **JobSkillRepository**: Interface để thao tác với bảng **job_skill**.
  - **CandidateSkillRepository**: Interface để thao tác với bảng **candidate_skill**.
  - **EmailRepository**: Interface để thao tác với bảng **email**.
- Chưa hoàn thành:
  - **ExperienceRepository**: Interface để thao tác với bảng **experience**.
  
### 5. Tạo các trang web cho phép công ty đăng tin tuyển dụng
- **Trang tuyển dụng (Company)**: Cho phép công ty đăng các công việc, mô tả công việc, bao gồm các kỹ năng đi kèm.
- **Trang ứng viên (Candidate)**: Xem các các công việc phù hợp với skill của mình.

### 6. Các ứng viên khi log vào sẽ được gợi ý các công việc có kỹ năng phù hợp với mình
- **Gợi ý công việc (Candidate)**: Các công việc phù hợp với kỹ năng của ứng viên đã có trước.

### 7. Giúp công ty tìm các ứng viên có kỹ năng phù hợp rồi gửi mail mời
- Hệ thống sẽ tìm các ứng viên có kỹ năng phù hợp dựa vào job và skill có sẵn, sau đó gửi email mời họ ứng tuyển vào công việc đó.

## II. Kết quả đạt được

### 1. Các luồng màn hình (Chạy trên web)

- **Trang chủ (Chung)**: 
  ![Home Page](https://i.ibb.co/7yBBX9b/Home-Page.png)
  

- **Trang đăng nhập (Chung - Lấy email đăng nhập theo vai trò)**:
  ![Login Page](https://i.ibb.co/q9wqg4n/Sign-in.png)


- **Trang tuyển dụng (Phía company) + Tìm kiếm**:
  ![Company Page](https://i.ibb.co/qWr0M7B/Company-Job-List.png)


- **Trang tạo công việc mới (Phía company)**:
  ![Create Job Page](https://i.ibb.co/xGgPTYr/Post-New-Job.png)


- **Trang tìm kiếm candidate phù hợp dựa theo các tiêu chí (Phía company)**:
  ![Find The Suitable Candidate](https://i.ibb.co/71LK3RF/Find-The-Suitable-Candidate.png)


- **Trang kết quả sau khi tìm kiếm candidate + gửi mail (Phía company)**:
  ![Result Page](https://i.ibb.co/CtZD4Fs/Commany-Result.png)


- **Trang ứng viên (Candidate)**:
  ![Candidate Page](https://i.ibb.co/Zx6ZN7Y/Candidate-Job-List.png)


- **Trang hộp thư email (Candidate)**:
  ![Candidate Mail](https://i.ibb.co/17qfVZQ/Candidate-Mail-Inbox.png)


## III. Ưu điểm - Nhược điểm
  ### 1. Ưu điểm:
  - **Dễ dàng sử dụng**: Giao diện dễ sử dụng, dễ hiểu.
  - **Tổ chức dữ liệu rõ ràng**: Cấu trúc cơ sở dữ liệu được phân chia rõ ràng theo các bảng tương ứng. Giúp dễ dàng quản lý và truy vấn dữ liệu liên quan.
  - **Gửi email mời tự động**: Công ty có thể tự động gửi email mời ứng viên phù hợp, tiết kiệm thời gian.
  - **Gợi ý công việc phù hợp**: Hệ thống sẽ gợi ý công việc phù hợp với kỹ năng của ứng viên.
  - **Tính năng tự động đồng bộ hóa dữ liệu khai thác**: Dữ liệu được tự động đồng bộ hóa từ file arff, giúp quá trình khai thác dữ liệu dễ dàng hơn.

  ### 2. Nhược điểm:
  - **Chưa hoàn thiện**: Một số chức năng còn hạn chế, giao diện chưa tối ưu, khó khăn trong áp dụng kết hợp nhiều thuật toán Machine Learning
  - **Dữ liệu lớn**: Vì tổng hợp dữ liệu qua file xử lý nên dữ liệu khá lớn
  - **Chưa tối ưu bảo mật**: Chưa tối ưu hóa quá trình bảo mật (Security)

  ### 3. Kết luận:
  - Bài Lab này cung cấp một nền tảng tốt cho việc quản lý công ty và ứng viên, với các tính năng mạnh mẽ như tự động gửi email và gợi ý công việc. Tuy nhiên có một số điều cần chú ý trong việc tối ưu hóa thiết kế cơ sở dữ liệu, phát triển giao diện người dùng và bảo mật để hệ thống hoạt động hiệu quả và an toàn.

## IV. Hướng phát triển
  - **Tối ưu hóa cơ sở dữ liệu**: Tối ưu hóa cơ sở dữ liệu để giảm dung lượng.
  - **Phát triển giao diện**: Phát triển giao diện động để thu hút người truy cập.
  - **Thêm chức năng**: Phát triển chức năng mới bao gồm Chatbot AI. 
  - **Bảo mật**: Tối ưu hóa bảo mật tốt hơn để hệ thống hoạt động an toàn.
  - **Đánh giá**: Thêm đánh giá và phản hồi cho công ty từ người dùng.
  - **Hỗ trợ ngôn ngữ**: Thêm nhiều ngôn ngữ khác nhau để hỗ trợ người dùng.