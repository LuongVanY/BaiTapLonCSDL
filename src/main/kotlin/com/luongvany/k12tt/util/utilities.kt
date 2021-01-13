package luongvany.k12tt.util

import com.luongvany.k12tt.model.Sex
import com.luongvany.k12tt.model.StaffEntry
import com.luongvany.k12tt.model.StaffEntryModel
import javafx.collections.ObservableList
import javafx.scene.image.Image
import javafx.scene.shape.Circle
import org.joda.time.DateTime
import java.time.LocalDate
import javax.swing.text.html.ImageView

fun DateTime.toJavaLocalDate(): LocalDate{
    return LocalDate.of(this.year, this.monthOfYear, this.dayOfMonth)
}
fun LocalDate.toDate(default: DateTime = DateTime(1900, 1, 1, 0, 0, 0)): DateTime{
    return DateTime(this.year, this.monthValue, this.dayOfMonth, 0, 0)
}
fun String.toLocalDate() = LocalDate.parse(this, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"))


fun StaffEntryModel.toStaffEntry() = StaffEntry(this.id.value, this.name.value, this.homeTown.value,
        this.sex.value, this.birthDay.value, this.departmentId.value,
        this.salaryId.value, this.img.value)

//fun HoiDongQuanTriEntryModel.toHoiDongQTEntry() = HoiDongQuanTriEntry(this.maHoiDongQuanTri.value, this.nhiemKy.value)
//
//fun ThanhVienHDQTEntryModel.toThanhVienHDQTEntry() = ThanhVienHDQTEntry(this.id.value, this.name.value, this.soDienThoai.value, this.soChungMinh.value, this.hoiDongQuantri.value)
//
//fun PhuCapEntryModel.toPhuCapEntry() = PhuCapEntry(this.id.value, this.maThanhVien.value, this.ngayDuocHuong.value, this.thongTinPhuCap.value)
//
//fun ChucVuEntryModel.toChucVuEntry() = ChucVuEntry(this.maChucVu.value, this.tenChucVu.value)
//
//fun HopDongLaoDongEntryModel.toHopDongEntry() = HopDongLaoDongEntry(this.id.value, this.ngayThanhLap.value)
//
//fun HDLD_DKEntryModel.toHDLD_DKEntry() = HDLD_DKEntry(this.maHDLD.value, this.maDieuKhoan.value)
//
//fun DieuKhoanLaoDongEntryModel.toDieuKhoanLaoDongEntry() = DieuKhoanLaoDongEntry(this.id.value, this.noiDung.value)
//
//fun DepartmentEntryModel.toDepartmentEntry() = DepartmentEntry(this.id.value, this.departmentName.value, this.managerId.value ,this.directorateId.value)
//
//fun DamNhiemEntryModel.toDamNhiemEntry() = DamNhiemEntry(this.maChucVu.value, this.maHopDong.value, this.maNhanVien.value)
//
//fun LuongEntryModel.toLuongEntry() = LuongEntry(this.id.value, this.bacLuong.value, this.luongCoBan.value, this.heSoLuong.value)
//
//fun DoiTacEntryModel.toDoiTacEntry() = DoiTacEntry(this.id.value, this.name.value, this.diaChi.value)
//
//fun HoaDonEntryModel.toHoaDonEntry() = HoaDonEntry(this.id.value, this.soTien.value, this.ngayLap.value)
//
//fun HangHoaEntryModel.toHangHoaEntry() = HangHoaEntry(this.maHangHoa.value, this.ten.value, this.gioiTinh.value, this.namSinh.value)
//
//fun NhapHangEntryModel.toNhapHangEntry() = NhapHangEntry(this.id.value, this.maNhanVien.value, this.maHoaDon.value, this.maHangHoa.value, this.maDoiTac.value)
//
//fun KhachHangEntryModel.toKhachHangEntry() = KhachHangEntry(this.id.value, this.diaChi.value, this.tenKhachHang.value, this.soThich.value, this.soDienThoai.value)
//
//fun HopDongEntryModel.toHopDongEntry() = HopDongEntry(this.id.value, this.thoiGianThucHien.value)
//
//fun DieuKhoanEntryModel.toDieuKhoanEntry() = DieuKhoanEntry(this.id.value, this.noiDung.value)
//
//fun HDGD_DKEntryModel.toHDGD_DKEntry() = HDGD_DKEntry(this.maHopDong.value, this.maDieuKhoan.value)
//
//fun GioiThieuEntryModel.toGioiThieuEntry() = GioiThieuEntry(this.maGioiThieu.value, this.maNhanVien.value, this.maHopDong.value, this.maHangHoa.value, this.maKhachHang.value)
//
fun String.convertToSex(): Sex {
    return if(this.toLowerCase().contains("nam"))
        Sex.NAM
    else if(this.toLowerCase().contains("nu"))
        Sex.NU
    else
        Sex.GIOITINHTHUBA
}
//
//fun DamNhiemEntryModel.coppy() = DamNhiemEntryModel().apply{
//    item = this@coppy.toDamNhiemEntry()
//}
//
//fun ChucVuEntryModel.coppy() = ChucVuEntryModel().apply {
//    item = this@coppy.toChucVuEntry()
//}
//
//fun HoiDongQuanTriEntryModel.coppy() = HoiDongQuanTriEntryModel().apply {
//    item = this@coppy.toHoiDongQTEntry()
//}
//
//fun StaffEntryModel.coppy() = StaffEntryModel().apply {
//    item = this@coppy.toStaffEntry()
//}
//
fun StaffEntry.toStaffEntryModel(): StaffEntryModel = StaffEntryModel().apply {
    item = this@toStaffEntryModel
}
//
//fun ThanhVienHDQTEntryModel.coppy() = ThanhVienHDQTEntryModel().apply{
//    item = this@coppy.toThanhVienHDQTEntry()
//}
//
//fun PhuCapEntryModel.coppy() = PhuCapEntryModel().apply {
//    item = this@coppy.toPhuCapEntry()
//}
//
//fun HopDongLaoDongEntryModel.coppy() = HopDongLaoDongEntryModel().apply {
//    item = this@coppy.toHopDongEntry()
//}
//
//fun DieuKhoanLaoDongEntryModel.coppy() = DieuKhoanLaoDongEntryModel().apply {
//    item = this@coppy.toDieuKhoanLaoDongEntry()
//}
//
//fun HDLD_DKEntryModel.coppy() = HDLD_DKEntryModel().apply{
//    item = this@coppy.toHDLD_DKEntry()
//}
//
//fun DepartmentEntryModel.coppy() = DepartmentEntryModel().apply {
//    item = this@coppy.toDepartmentEntry()
//}
//
//fun LuongEntryModel.coppy() = LuongEntryModel().apply {
//    item = this@coppy.toLuongEntry()
//}
//
//fun DoiTacEntryModel.coppy() = DoiTacEntryModel().apply {
//    item = this@coppy.toDoiTacEntry()
//}
//
//fun HoaDonEntryModel.coppy() = HoaDonEntryModel().apply {
//    item = this@coppy.toHoaDonEntry()
//}
//
//fun HangHoaEntryModel.coppy() = HangHoaEntryModel().apply {
//    item = this@coppy.toHangHoaEntry()
//}
//
//fun NhapHangEntryModel.coppy() = NhapHangEntryModel().apply {
//    item = this@coppy.toNhapHangEntry()
//}
//
//fun KhachHangEntryModel.coppy() = KhachHangEntryModel().apply {
//    item = this@coppy.toKhachHangEntry()
//}
//
//fun HopDongEntryModel.coppy() = HopDongEntryModel().apply {
//    item = this@coppy.toHopDongEntry()
//}
//
//fun DieuKhoanEntryModel.coppy() = DieuKhoanEntryModel().apply {
//    item = this@coppy.toDieuKhoanEntry()
//}
//
//fun HDGD_DKEntryModel.coppy() = HDGD_DKEntryModel().apply {
//    item = this@coppy.toHDGD_DKEntry()
//}
//
//fun GioiThieuEntryModel.coppy() = GioiThieuEntryModel().apply {
//    item = this@coppy.toGioiThieuEntry()
//}
//fun ObservableList<String>.addEle(string: String) = this.add(string)
//
//fun StaffEntryModel.showThumbnail() = CircleImage("/${this.img.value}", 40.0)

class CircleImage(url: String, size: Double): javafx.scene.image.ImageView(){
    init {
        fitWidth = size
        isPreserveRatio = true
        isSmooth = true
        isCache = true

        this.image = Image(url)
        val glass = Circle(size/2, size/2, size/2)
        clip = glass
    }
}