package com.jurcik.coding_exercise.repository;

import com.jurcik.coding_exercise.jooq.tables.Users;
import com.jurcik.coding_exercise.jooq.tables.records.UsersRecord;
import com.jurcik.coding_exercise.util.UserRole;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {

    private final DSLContext dslContext;

    public UserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Optional<UsersRecord> getUser(UUID id) {
        return dslContext.selectFrom(Users.USERS).where(Users.USERS.ID.eq(id)).fetchOptional();
    }

    public Optional<UsersRecord> findUser(String username) {
        return dslContext.selectFrom(Users.USERS).where(Users.USERS.USERNAME.equalIgnoreCase(username)).fetchOptional();
    }

    public UsersRecord createUser(String name, String username, String encodedPassword, UserRole role) {
        return dslContext.insertInto(Users.USERS).columns(Users.USERS.NAME, Users.USERS.USERNAME, Users.USERS.PASSWORD, Users.USERS.ROLE).values(name, username, encodedPassword, role).returning().fetchOne();
    }

    public void modifyName(UUID userId, String newName) {
        dslContext.update(Users.USERS).set(Users.USERS.NAME, newName).where(Users.USERS.ID.eq(userId)).execute();
    }

    public void modifyUsername(UUID userId, String newUsername) {
        dslContext.update(Users.USERS).set(Users.USERS.USERNAME, newUsername).where(Users.USERS.ID.eq(userId)).execute();
    }

    public void modifyPassword(UUID userId, String newEncryptedPassword) {
        dslContext.update(Users.USERS).set(Users.USERS.PASSWORD, newEncryptedPassword).where(Users.USERS.ID.eq(userId)).execute();
    }

    public void modifyRole(UUID userId, UserRole role) {
        dslContext.update(Users.USERS).set(Users.USERS.ROLE, role).where(Users.USERS.ID.eq(userId)).execute();
    }

    public void deleteUser(UUID userId) {
        dslContext.deleteFrom(Users.USERS).where(Users.USERS.ID.eq(userId)).execute();
    }

    public UsersRecord[] listUsers() {
        return dslContext.selectFrom(Users.USERS).fetchArray();
    }

    public boolean userExists(String username) {
        return dslContext.selectFrom(Users.USERS).where(Users.USERS.USERNAME.eq(username)).fetch().isNotEmpty();
    }
}
