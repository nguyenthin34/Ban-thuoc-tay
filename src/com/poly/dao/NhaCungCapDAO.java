/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.NhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NhaCungCapDAO {
    public void insert (NhaCungCap model) throws Exception {
        String sql = "insert into NhaCungCap values(?,?,?,?,?,?,?)";
        SQLConnect.executeUpdate(sql, model.getID_NhaCC(), model.getTen_NCC(),
                                      model.getEmail_NCC(), model.getSDT_NCC(),
                                      model.getDiaChi(), model.getNgayTao(),
                                      model.isTrangThai());
    }
    
    public void update (NhaCungCap model) throws Exception{
        String sql = "update NhaCungCap set Ten_NCC = ?, Email_NCC = ?, SDT_NCC = ?, DiaChi_NCC = ?,"
                + " NgayTao = ?, TrangThai = ? "
                + " where ID_NhaCC = ?";
        SQLConnect.executeUpdate(sql, model.getTen_NCC(), 
                                      model.getEmail_NCC(),
                                      model.getSDT_NCC(),
                                      model.getDiaChi(),
                                      model.getNgayTao(),
                                      model.isTrangThai() ? 1 : 0,
                                      model.getID_NhaCC());
    }
    
    public List<NhaCungCap> select() {
        String sql = "select * from NhaCungCap";
        return select(sql);
    }
     public List<NhaCungCap> select_tt() {
        String sql = "select * from NhaCungCap where TrangThai = 1";
        return select(sql);
    }
    public NhaCungCap findByID(String ID_DVT) {
        String sql = "select * from NhaCungCap where ID_NhaCC = ?";
        List<NhaCungCap> list = select(sql, ID_DVT);
        return list.size() > 0 ? list.get(0) : null;
    }
    private List<NhaCungCap> select(String sql, Object... args) {
        List<NhaCungCap> list = new ArrayList<>();
        
        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    NhaCungCap model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }
            
        } catch (Exception e) {
            
        }
        return list;
    }
    
    private NhaCungCap readFromResult(ResultSet rs) throws SQLException {
        NhaCungCap model = new NhaCungCap();
        model.setID_NhaCC(rs.getString(1));
        model.setTen_NCC(rs.getString(2));
        model.setEmail_NCC(rs.getString(3));
        model.setSDT_NCC(rs.getString(4));
        model.setDiaChi(rs.getString(5));
        model.setNgayTao(rs.getDate(6));
        model.setTrangThai(rs.getBoolean(7));
        return model;
    }
}
