package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Npc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcNpcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Npc> findAll() {
        String sql = "SELECT * FROM npcs";
        return jdbcTemplate.query(sql, this::mapRowToNpc);
    }

    public Npc findById(int id) {
        String sql = "SELECT * FROM npcs WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToNpc, id);
    }

    public String findFirstNameById(int firstNameId) {
        String sql = "SELECT first_name FROM first_names WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, firstNameId);
    }

    public String findLastNameById(int lastNameId) {
        String sql = "SELECT last_name FROM last_names WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, lastNameId);
    }

    public String findRaceById(int raceId) {
        String sql = "SELECT race_name FROM races WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, raceId);
    }

    public String findProfessionById(int professionId) {
        String sql = "SELECT profession_name FROM professions WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, professionId);
    }

    public String findSocialStatusById(int socialStatusId) {
        String sql = "SELECT status_name FROM social_statuses WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, socialStatusId);
    }

    public String findGenderById(int genderId) {
        String sql = "SELECT gender_name FROM genders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, genderId);
    }

    public List<String> findLanguagesByNpcId(int npcId) {
        String sql = "SELECT l.language_name " +
                "FROM npc_languages nl " +
                "JOIN languages l ON nl.language_id = l.id " +
                "WHERE nl.npc_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("language_name"), npcId);
    }

    public void save(Npc npc) {
        if (npc.getId() == 0) {
            String insertSql = "INSERT INTO npcs (first_name_id, last_name_id, age, race_id, social_status_id, profession_id, gender_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(insertSql, npc.getFirstNameId(), npc.getLastNameId(), npc.getAge(), npc.getRaceId(), npc.getSocialStatusId(), npc.getProfessionId(), npc.getGenderId());
        } else {
            String updateSql = "UPDATE npcs SET first_name_id = ?, last_name_id = ?, age = ?, race_id = ?, social_status_id = ?, profession_id = ?, gender_id = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, npc.getFirstNameId(), npc.getLastNameId(), npc.getAge(), npc.getRaceId(), npc.getSocialStatusId(), npc.getProfessionId(), npc.getGenderId(), npc.getId());
        }
    }

    public void deleteById(int id) {
        String deleteSql = "DELETE FROM npcs WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }

    private Npc mapRowToNpc(ResultSet rs, int rowNum) throws SQLException {
        return new Npc(
                rs.getInt("id"),
                rs.getInt("first_name_id"),
                rs.getInt("last_name_id"),
                rs.getInt("age"),
                rs.getInt("race_id"),
                rs.getInt("social_status_id"),
                rs.getInt("profession_id"),
                rs.getInt("gender_id"),
                null // Languages can be fetched separately if needed
        );
    }
}
