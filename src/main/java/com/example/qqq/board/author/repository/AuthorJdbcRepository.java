package com.example.qqq.board.author.repository;

import com.example.qqq.board.author.domain.Author;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorJdbcRepository {
    private final DataSource dataSource;

    public AuthorJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    } //db에서 가져오기




    public void save(Author author){
        try {
            Connection connection =dataSource.getConnection();
            String sql ="insert into authortest(name,email,password) values(?,?,?)";
            PreparedStatement ps =connection.prepareStatement(sql);
            ps.setString(1, author.getName());
            ps.setString(2, author.getEmail());
            ps.setString(3, author.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//원래는
//public class AuthorRepository {
//    public void save(Author author){
//        this.authorList.add(author); <-여기가 insert문 sql로 넣는다
//    }
//이렇게만 존재한다 -> 이렇게 db넘어가고 id생성



    //    3-2
    public List<Author>findAll() {
        List<Author> authorList = new ArrayList<>();
        Connection connection = null;
            try {
                connection = dataSource.getConnection();
                String sql = "select*from authortest";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    Author author = Author.builder()
                            .id(id)
                            .name(name)
                            .email(email)
                            .password(password)
                            .build();
                    authorList.add(author);
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return authorList;
        }




    //3-3
    public Optional<Author> findById(Long inputid){
        Author author =null;

        Connection connection = null;

        try {
            connection=dataSource.getConnection();
            String sql = "select * from authortest where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,inputid);
            ResultSet rs =ps.executeQuery();
            if(rs.next()) { Long id = rs.getLong("id");//여긴 데이터베이스 컬럼명이랑 같아야한다 //Author도 그냥 변수명 같게해라 안헷갈리게
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                author =Author.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .password(password)
                        .build();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(author);
    }

}//여기도 원래 sql문



