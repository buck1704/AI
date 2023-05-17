# Lập trình cho bài toán TSP, môn trí tuệ nhân tạo, lớp 71DCTT21, utt
# HƯỚNG DẪN SỬ DỤNG:
  - Tải git ở đường dẫn: https://git-scm.com/       <bỏ qua bước này nếu đã có>
  - Vào github, sau đó tìm <>code để lấy đường dẫn.
  - Mở netbeans, ở thanh header tìm: team -> git -> remote -> clone. sau đó paste đường dẫn tới github lấy được ở trên.
  - Trỏ chuột file vào file dulieu.inp ở packet data, sau đó chọn properties. tìm dòng all file để copy đường dẫn tới file dulieu.inp.
  - ở class menu.java, thay đổi địa chỉ thành địa chỉ của packet data từ dòng 15 -> 20 bằng đường dẫn lấy được ở trên.

# KIỂM TRA ĐÃ CÓ GIT TRONG MÁY HAY CHƯA:
  - Mở Command Prompt hoặc Terminal 
  - Gõ lệnh sau:  git --version
    => Lệnh này sẽ hiển thị phiên bản của Git nếu Git đã được cài đặt. Nếu bạn thấy thông tin về phiên bản Git, điều đó có nghĩa là Git đã được cài đặt thành công trên laptop của bạn.
    => Nếu Git chưa được cài đặt, bạn sẽ nhận được thông báo lỗi hoặc lệnh không được tìm thấy.
# Đề tài:
  Bài toán yêu cầu thiết lập lộ trình du lịch cho người khách đi thăm n thành phố được đánh số từ 1 đến n sau đó quay lại thành phố xuất phát, sao cho mỗi thành phố được đi qua đúng 1 lần. Mạng lưới giao thông giữa các thành phố này là hai chiều, được mô tả bằng ma trận A[i,j] với A[i,j]=1 nếu có đường đi từ thành phố i đến thành phố j, và A[i,j]=0 trong trường hợp ngược lại.
  Dữ liệu đầu vào của bài toán được đọc từ file DULIEU.INP, gồm các thông tin như sau:
    - Dòng 1: Ghi số nguyên n (n<=20), đại diện cho số lượng thành phố cần thăm.	
    - Dòng thứ i+1 (1<=i<=n): Ghi n số nguyên không âm (0 hoặc 1), mô tả hàng i của ma trận A, trong đó số 1 tại vị trí j thể hiện có đường đi từ thành phố i đến thành phố j, và số 0 thể hiện không có đường đi từ thành phố i đến thành phố j.
  Yêu cầu: của bài toán là xuất ra chu trình đường đi (chu trình Hamilton) hoặc thông báo nếu không tồn tại chu trình đường đi thỏa mãn yêu cầu.
