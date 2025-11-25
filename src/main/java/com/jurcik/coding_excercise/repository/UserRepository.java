package com.jurcik.coding_excercise.repository;

import com.jurcik.coding_excercise.jooq.tables.Users;
import com.jurcik.coding_excercise.jooq.tables.records.UsersRecord;
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

    public UsersRecord createUser(String name) {
        return dslContext.insertInto(Users.USERS).columns(Users.USERS.ID, Users.USERS.NAME).values(UUID.randomUUID(), name).returning().fetchOne();
    }

    public void modifyName(UUID userId, String newName) {
        dslContext.update(Users.USERS).set(Users.USERS.NAME, newName).where(Users.USERS.ID.eq(userId)).execute();
    }

    public void deleteUser(UUID userId) {
        dslContext.deleteFrom(Users.USERS).where(Users.USERS.ID.eq(userId)).execute();
    }

    public UsersRecord[] listUsers() {
        return dslContext.selectFrom(Users.USERS).fetchArray();
    }
}
