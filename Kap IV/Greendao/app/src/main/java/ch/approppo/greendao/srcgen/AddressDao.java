package ch.approppo.greendao.srcgen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADDRESS".
*/
public class AddressDao extends AbstractDao<Address, Long> {

    public static final String TABLENAME = "ADDRESS";

    /**
     * Properties of entity Address.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Street = new Property(1, String.class, "street", false, "STREET");
        public final static Property StreetNumber = new Property(2, Integer.class, "streetNumber", false, "STREET_NUMBER");
        public final static Property Zip = new Property(3, String.class, "zip", false, "ZIP");
        public final static Property City = new Property(4, String.class, "city", false, "CITY");
        public final static Property PersonId = new Property(5, long.class, "personId", false, "PERSON_ID");
    };

    private DaoSession daoSession;

    private Query<Address> person_AddressesQuery;

    public AddressDao(DaoConfig config) {
        super(config);
    }
    
    public AddressDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADDRESS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"STREET\" TEXT," + // 1: street
                "\"STREET_NUMBER\" INTEGER," + // 2: streetNumber
                "\"ZIP\" TEXT," + // 3: zip
                "\"CITY\" TEXT," + // 4: city
                "\"PERSON_ID\" INTEGER NOT NULL );"); // 5: personId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADDRESS\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Address entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String street = entity.getStreet();
        if (street != null) {
            stmt.bindString(2, street);
        }
 
        Integer streetNumber = entity.getStreetNumber();
        if (streetNumber != null) {
            stmt.bindLong(3, streetNumber);
        }
 
        String zip = entity.getZip();
        if (zip != null) {
            stmt.bindString(4, zip);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(5, city);
        }
        stmt.bindLong(6, entity.getPersonId());
    }

    @Override
    protected void attachEntity(Address entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Address readEntity(Cursor cursor, int offset) {
        Address entity = new Address( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // street
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // streetNumber
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // zip
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // city
            cursor.getLong(offset + 5) // personId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Address entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStreet(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStreetNumber(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setZip(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCity(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPersonId(cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Address entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Address entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "addresses" to-many relationship of Person. */
    public List<Address> _queryPerson_Addresses(long personId) {
        synchronized (this) {
            if (person_AddressesQuery == null) {
                QueryBuilder<Address> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PersonId.eq(null));
                queryBuilder.orderRaw("T.'CITY' ASC");
                person_AddressesQuery = queryBuilder.build();
            }
        }
        Query<Address> query = person_AddressesQuery.forCurrentThread();
        query.setParameter(0, personId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPersonDao().getAllColumns());
            builder.append(" FROM ADDRESS T");
            builder.append(" LEFT JOIN PERSON T0 ON T.\"PERSON_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Address loadCurrentDeep(Cursor cursor, boolean lock) {
        Address entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Person person = loadCurrentOther(daoSession.getPersonDao(), cursor, offset);
         if(person != null) {
            entity.setPerson(person);
        }

        return entity;    
    }

    public Address loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Address> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Address> list = new ArrayList<Address>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Address> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Address> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
