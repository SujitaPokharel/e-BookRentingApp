package com.npu.libraryapp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.npu.libraryapp.dao.ConnectionDAO;
import com.npu.libraryapp.dao.MemberDAO;
import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.domain.BookIssue;
import com.npu.libraryapp.domain.Member;
import com.npu.libraryapp.exceptions.MemberDaoException;

@Repository("memberDaoJdbc")
@Transactional
public class MemberDaoJdbcImpl implements MemberDAO  {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private NamedParameterJdbcTemplate namedTemplate;
	private MemberRowMapper memberRowMapper;
	private MemberMapper memberMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
							.withTableName("member")
							.usingGeneratedKeyColumns("id");
		memberRowMapper = new MemberRowMapper();
		memberMapper = new MemberMapper();
	}

	public int getMemberCount() {
		String sql = "select count(*) from Member";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public void insertMember(Member member) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(member);
		Number newId = jdbcInsert.executeAndReturnKey(params);
		member.setMemid(newId.intValue());
	}

	public String findMemberNameById(int id) {
		String sql = "select name from Member where memid=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);	
		
	}

	public Member findMemberByName(String name) {
		int memberFound;
		String sql = "select * from Member where name = ?";
		List<Member> memberList = jdbcTemplate.query(sql, memberRowMapper, name );
		
		memberFound = memberList.size();
		if (memberFound == 1) {
			return memberList.get(0);
		} else if (memberFound == 0) {
			return null;
		}
		throw new MemberDaoException("Multiple Member Found with Same Name");
	}


	
	public List<Member> findMemberById(int id) {
		String sql = "select name from member where memid =? ";
		List<Member> list = jdbcTemplate.query(sql, memberMapper , id );
		
		System.out.println(list);
		
		return list;
	}
	
	
	public int deleteByMemberId(int id) {
		String sql = "DELETE from member where memid = ? " ;
		int rowsRemoved = jdbcTemplate.update(sql, id);
		return rowsRemoved;
	}
		
	
	@Override
	public boolean validate(Member member)
	{
		String SQL = "select * from member where username = '"+member.getUserName()+"' and password = '"+member.getPassword()+"';";
		System.out.println("SQL : "+ SQL);
		Statement stmt;
		try
		{
			stmt = ConnectionDAO.getStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			if (null!= rs && rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			System.out.println("SQL Exception : "+e.getMessage());
			return false;
		}
	}

	@Override
	public List<Member> findAllMembers(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
//	public void insertEntry(Member member) {
//		int id;
//		SqlParameterSource params = new BeanPropertySqlParameterSource(member);
//		Number newId = jdbcInsert.executeAndReturnKey(params);		
//
//	}
	
	
}


