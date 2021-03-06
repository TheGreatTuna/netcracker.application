package com.gmail.netcracker.application.dto.dao.imp;

import com.gmail.netcracker.application.dto.dao.interfaces.EventDao;
import com.gmail.netcracker.application.dto.model.Event;
import com.gmail.netcracker.application.dto.model.Participant;
import com.gmail.netcracker.application.dto.model.User;
import com.gmail.netcracker.application.utilites.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class EventDaoImpl extends ModelDao implements EventDao {
    @Value("${sql.event.pkColumnName}")
    private String PK_COLUMN_NAME;

    @Value("${sql.event.add}")
    private String SQL_ADD;

    @Value("${sql.event.delete}")
    private String SQL_DELETE;

    @Value("${sql.event.find}")
    private String SQL_FIND;

    @Value("${sql.event.findPrivateEvents}")
    private String SQL_FIND_PRIVATE_EVENTS;

    @Value("${sql.event.findAvailableEvents}")
    private String SQL_FIND_AVAILABLE_EVENTS;

    @Value("${sql.event.findDrafts}")
    private String SQL_FIND_DRAFTS;

    @Value("${sql.event.update}")
    private String SQL_UPDATE;

    @Value("${sql.event.findPersonEvents}")
    private String SQL_FIND_PERSON_EVENTS;

    @Value("${sql.event.participate}")
    private String SQL_PARTICIPATE;

    @Value("${sql.event.countParticipants}")
    private String SQL_COUNT_PARTICIPANTS;

    @Value("${sql.event.getParticipants}")
    private String SQL_GET_PARTICIPANTS;

    @Value("${sql.event.isParticipantOfEvent}")
    private String SQL_IS_PARTICIPANT_OF_EVENT;

    @Value("${sql.event.unsubscribe}")
    private String SQL_UNSUBSCRIBE;

    @Value("${sql.event.findCreatedFriendsEvents}")
    private String SQL_FIND_CREATED_FRIENDS_EVENTS;

    @Value("${sql.event.findCreatedPublicEvents}")
    private String SQL_FIND_CREATED_PUBLIC_EVENTS;

    @Value("${sql.event.maxid}")
    private String SQL_MAX_ID;

    @Value("${sql.event.getEventType}")
    private String SQL_GET_EVENT_TYPE;

    @Value("${sql.event.getCreator}")
    private String SQL_GET_CREATOR;

    @Value("${sql.event.checkCreator}")
    private String SQL_CHECK_CREATOR;

    @Value("${sql.event.withPriority}")
    private String SQL_GET_EVENT_WITH_PRIORITY;

    @Value("${sql.event.allWithPriority}")
    private String SQL_GET_EVENTS_WITH_PRIORITY;

    @Value("${sql.event.convertDraft}")
    private String SQL_CONVERT_DRAFT;

    @Value("${sql.event.searchPublic}")
    private String SQL_EVENT_SEARCH;

    @Value("${sql.event.searchForUser}")
    private String SQL_USER_EVENT_SEARCH;

    @Value("${sql.event.getAllPersonEvents}")
    private String SQL_GET_ALL_PERSON_EVENTS;

    @Value("${sql.event.getEventsByUserFromRange}")
    private String SQL_FROM_RANGE_BY_USER;

    @Value("${sql.event.getEventsFromRange}")
    private String SQL_GET_EVENTS_FROM_RANGE;

    @Value("${sql.event.getUsersToInvite}")
    private String SQL_GET_USERS_TO_INVITE;

    @Value("${sql.event.findFriendsForInvite}")
    private String SQL_FIND_FRIENDS_FOR_INVITE;

    @Autowired
    private RowMapper<Event> eventRowMapper;
    @Autowired
    private RowMapper<User> friendRowMapper;
    @Autowired
    private RowMapper<Participant> participantRowMapper;

    @Autowired
    public EventDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void update(Event event) {
        updateEntity(SQL_UPDATE,
                event.getName(),
                event.getDescription(),
                Utilities.parseStringToTimestamp(event.getDateStart()),
                event.getDuration(),
                Utilities.parseStringToTimestamp(event.getEndRepeat()),
                event.getType(),
                event.isDraft(),
                event.getWidth(),
                event.getLongitude(),
                event.getEventPlaceName(),
                event.getPeriodicity(),
                event.getPhoto(),
                event.getEventId());
    }

    @Override
    public void delete(Long eventId) {
        deleteEntity(SQL_DELETE, eventId);
    }

    @Override
    public void insertEvent(Event event) {
        event.setEventId(insertEntity(SQL_ADD, PK_COLUMN_NAME,
                event.getName(),
                event.getDescription(),
                event.getCreator(),
                Utilities.parseStringToTimestamp(event.getDateStart()),
                event.getDuration(),
                Utilities.parseStringToTimestamp(event.getEndRepeat()),
                event.getWidth(),
                event.getLongitude(),
                event.getEventPlaceName(),
                event.getPeriodicity(),
                event.getType(),
                event.isDraft(),
                event.getPhoto()));
    }

    @Override
    public Event getEvent(Long eventId) {
        return findEntity(SQL_FIND, eventRowMapper, eventId);
    }

    @Override
    public List<Event> findPrivateEvents(Long userId) {
        return findEntityList(SQL_FIND_PRIVATE_EVENTS, eventRowMapper, userId);
    }

    @Override
    public List<Event> findAvailableEvents(Long userId) {
        return findEntityList(SQL_FIND_AVAILABLE_EVENTS, eventRowMapper, userId, userId);
    }

    @Override
    public List<Event> findDrafts(Long userId) {
        return findEntityList(SQL_FIND_DRAFTS, eventRowMapper, userId);
    }

    @Override
    public List<Event> findEventSubscriptions(Long personId) {
        return findEntityList(SQL_FIND_PERSON_EVENTS, eventRowMapper, personId);
    }

    @Override
    public void participate(Long userId, Long eventId) {
        updateEntity(SQL_PARTICIPATE, userId, eventId);
    }

    @Override
    public Long getParticipantsCount(Long eventId) {
        return countRows(SQL_COUNT_PARTICIPANTS, eventId);
    }

    @Override
    public List<User> getParticipants(Long eventId) {
        return findEntityList(SQL_GET_PARTICIPANTS, friendRowMapper, eventId);
    }

    @Override
    public Participant isParticipantOfEvent(Long id, Long eventId) {
        return findEntity(SQL_IS_PARTICIPANT_OF_EVENT, participantRowMapper, id, eventId);
    }

    @Override
    public void unsubscribe(Long userId, Long eventId) {
        deleteEntity(SQL_UNSUBSCRIBE, userId, eventId);
    }

    @Override
    public List<Event> findCreatedFriendsEvents(Long id) {
        return findEntityList(SQL_FIND_CREATED_FRIENDS_EVENTS, eventRowMapper, id);
    }

    @Override
    public List<Event> findCreatedPublicEvents(Long id) {
        return findEntityList(SQL_FIND_CREATED_PUBLIC_EVENTS, eventRowMapper, id);
    }

    @Override
    public Long getEventType(Long eventId) {
        return countRows(SQL_GET_EVENT_TYPE, eventId);
    }

    @Override
    public User getCreator(Long eventId) {
        return findEntity(SQL_GET_CREATOR, friendRowMapper, eventId);
    }

    @Override
    public Event checkCreatorById(Long personId, Long eventId) {
        return findEntity(SQL_CHECK_CREATOR, eventRowMapper, personId, eventId);
    }

    public Event findEventSubscriptionsWithPriority(Long personId, Long eventId) {
        return findEntity(SQL_GET_EVENT_WITH_PRIORITY, eventRowMapper, personId, eventId);
    }

    public List<Event> listEventsWithPriority(Long personId) {
        return findEntityList(SQL_GET_EVENTS_WITH_PRIORITY, eventRowMapper, personId);
    }

    @Override
    public void convertDraftToEvent(Event event) {
        updateEntity(SQL_CONVERT_DRAFT,
                event.getDraft(),
                event.getEventId());
    }

    @Override
    public List<Event> searchInPublic(String query, Long userId) {
        return findEntityList(SQL_EVENT_SEARCH, eventRowMapper, query, userId);
    }

    @Override
    public List<Event> searchInUsersEvents(String query, Long userId) {
        return findEntityList(SQL_USER_EVENT_SEARCH, eventRowMapper, 100, 0, query, userId);
    }

    @Override
    public List<Event> getAllPersonEvents(Long id) {
        return findEntityList(SQL_GET_ALL_PERSON_EVENTS, eventRowMapper, id);
    }

    @Override
    public List<Event> searchByUserFromRange(Long userId, Timestamp start, Timestamp end) {
        if (start.before(end) || start.equals(end))
            return findEntityList(SQL_FROM_RANGE_BY_USER, eventRowMapper, userId, end, start, end);
        else return searchByUserFromRange(userId, end, start);
    }

    @Override
    public List<Event> getEventsFromRange(Timestamp fromDate, Timestamp tillDate, Long id) {
        return findEntityList(SQL_GET_EVENTS_FROM_RANGE, eventRowMapper, id, fromDate, tillDate);
    }

    @Override
    public List<User> findUsersForInvite(Long id, Long eventId) {
        return findEntityList(SQL_GET_USERS_TO_INVITE, friendRowMapper, id, eventId);
    }

    @Override
    public List<User> findFriendsForInvite(Long id, Long eventId) {
        return findEntityList(SQL_FIND_FRIENDS_FOR_INVITE, friendRowMapper, id, id, eventId);
    }
}

