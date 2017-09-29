package daos;

import models.Event;
import play.Logger;
import play.db.Database;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventDAO {
    private static final String FIND_BY_ID_QUERY = "Select * from event_by_user where id = ?";
    private static final String FIND_WITH_FILTERS_QUERY = "Select * from event_by_user ";
//    private static final String SAVE_QUERY = "Insert into event_by_user (tenant, user_id, event_time, operation, " +
//            "user_role, identifier, action, entity, entity_id, session_id, details) values (?,?,?,?,?,?,?,?,?,?,?)";
private static final String SAVE_QUERY = "Insert into event_by_user (tenant, user_id, details) values (?,?,?)";

    @Inject
    Database db;

    public Event findById(Long id) throws Exception {
        Connection connection = db.getConnection();
        PreparedStatement findByIdStatement = null;
        ResultSet resultSet = null;
        try {

            findByIdStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            findByIdStatement.setLong(1, id);

            resultSet = findByIdStatement.executeQuery();
        } catch (SQLException e) {
            throw new Exception(String.format("There was a sql exception: %s", e.getMessage()));
        }

        return resultSet.next() ? mapToEvent(resultSet) : null;
    }

    public List<Event> findWithFilters(Optional<Long> tenant, Optional<Long> userId) throws Exception {
        Connection connection = db.getConnection();
        PreparedStatement findByIdStatement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder stringBuilder = new StringBuilder();

            boolean filtersExist = false, hasTenant = false, hasUserId = false;

            // Any filters?
            if(tenant.isPresent() || userId.isPresent()) {
                filtersExist = true;
            }

            if(filtersExist) {
                StringBuilder queryWithFilters = new StringBuilder(FIND_WITH_FILTERS_QUERY).append("where ");
                int parameterIndex = 1;

                if(tenant.isPresent()) {
                    hasTenant = true;
                    queryWithFilters.append("tenant = ?");

                    if(userId.isPresent()) {
                        hasUserId = true;
                        queryWithFilters.append(" AND user_id = ?");
                    }
                } else {
                    hasUserId = true;
                    queryWithFilters.append("user_id = ?");
                }

                findByIdStatement = connection.prepareStatement(queryWithFilters.toString());

                if(hasTenant) {
                    findByIdStatement.setLong(parameterIndex, tenant.get());
                    parameterIndex++;
                }

                if(hasUserId) {
                    findByIdStatement.setLong(parameterIndex, userId.get());
                    parameterIndex++;
                }
            } else {
                findByIdStatement = connection.prepareStatement(FIND_WITH_FILTERS_QUERY);
            }


            resultSet = findByIdStatement.executeQuery();
        } catch (SQLException e) {
            throw new Exception(String.format("There was a sql exception: %s", e.getMessage()));
        }

        return mapToListOfEvents(resultSet);
    }

    public boolean save(Event event) throws Exception {
        boolean saved = false;
        Connection connection = db.getConnection();
        PreparedStatement saveStatement = null;
        ResultSet resultSet = null;
        try {

            saveStatement = connection.prepareStatement(SAVE_QUERY);
            saveStatement.setLong(1, event.getTenantId());
            saveStatement.setLong(2, event.getUserId());
            /*saveStatement.setTimestamp(3, event.getTimestamp());
            saveStatement.setString(4, event.getOperation());
            saveStatement.setString(5, event.getUserRole());
            saveStatement.setLong(6, event.getIdentifier());
            saveStatement.setString(7, event.getAction());
            saveStatement.setString(8, event.getEntity());
            saveStatement.setLong(9, event.getEntityId());
            saveStatement.setString(10, event.getSessionId());*/
            saveStatement.setString(3, event.getLongText());


            int rowsAffected = saveStatement.executeUpdate();

            if(rowsAffected == 1)
                saved = true;
        } catch (SQLException e) {
            throw new Exception(String.format("There was a sql exception: %s", e.getMessage()));
        }

        return saved;
    }

    private Event mapToEvent(ResultSet resultSet) {
        Event employee = null;

        try {
            Long id = resultSet.getLong("id");
            Long tenantId = resultSet.getLong("tenant");
            Long userId = resultSet.getLong("user_id");
            Timestamp timestamp = resultSet.getTimestamp("event_time");
            String operation = resultSet.getString("operation");
            String userRole = resultSet.getString("user_role");
            Long identifier = resultSet.getLong("identifier");
            String action = resultSet.getString("action");
            String entity = resultSet.getString("entity");
            Long entityId = resultSet.getLong("entity_id");
            String sessionId = resultSet.getString("session_id");
            String details = resultSet.getString("details");

            employee = new Event(id, tenantId, userId, timestamp, operation, userRole, identifier, action, entity, entityId,
                    sessionId, details);
        } catch (SQLException e) {
            Logger.error("Error extracting values from query result: " + e.getMessage());
        }

        return employee;
    }

    private List<Event> mapToListOfEvents(ResultSet resultSet) {
        List<Event> events = new ArrayList<>();

        try {
            while(resultSet.next()) {
                Event event = mapToEvent(resultSet);

                if(event != null) {
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            Logger.error("Error extracting values from query result: " + e.getMessage());
        }

        return events;
    }
}
