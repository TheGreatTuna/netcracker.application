package com.gmail.netcracker.application.dto.dao.interfaces;

import com.gmail.netcracker.application.dto.model.Event;
import com.gmail.netcracker.application.dto.model.User;

import java.util.List;

public interface EventDao {
    void update(Event event);

    void delete(int eventId);

    void insertEvent(Event event);

    Event getEvent(int eventId);

    List<Event> eventList();

    List<Event> findPublicEvents();

    List<Event> findPrivateEvents(Long userId);

    List<Event> findFriendsEvents(Long userId);

    List<Event> findDrafts(Long userId);

    List<Event> getAllMyEvents(Long personId);

    void participate(Long user_id, long event_id);

    int getParticipantsCount(int eventId);

    List<User> getParticipants(long event_id);

    int isParticipated(Long id, int eventId);

    void unsubscribe(long id, long event_id);
}
