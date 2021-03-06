package ch.approppo.greendao.srcgen;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ADDRESS".
 */
public class Address {

    private Long id;
    private String street;
    private Integer streetNumber;
    private String zip;
    private String city;
    private long personId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient AddressDao myDao;

    private Person person;
    private Long person__resolvedKey;


    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Address(Long id, String street, Integer streetNumber, String zip, String city, long personId) {
        this.id = id;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zip = zip;
        this.city = city;
        this.personId = personId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAddressDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    /** To-one relationship, resolved on first access. */
    public Person getPerson() {
        long __key = this.personId;
        if (person__resolvedKey == null || !person__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonDao targetDao = daoSession.getPersonDao();
            Person personNew = targetDao.load(__key);
            synchronized (this) {
                person = personNew;
            	person__resolvedKey = __key;
            }
        }
        return person;
    }

    public void setPerson(Person person) {
        if (person == null) {
            throw new DaoException("To-one property 'personId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.person = person;
            personId = person.getId();
            person__resolvedKey = personId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
