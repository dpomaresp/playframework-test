package models;

import java.sql.Timestamp;

public class Event {
    private Long id;
    private Long tenantId;
    private Long userId;
    private Timestamp timestamp;
    private String operation;
    private String userRole;
    private Long identifier;
    private String action;
    private String entity;
    private Long entityId;
    private String sessionId;
    private String longText;

    public Event() {
    }

    public Event(Long id, Long tenantId, Long userId, Timestamp timestamp, String operation, String userRole,
                 Long identifier, String action, String entity, Long entityId, String sessionId, String longText) {
        this.id = id;
        this.tenantId = tenantId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.operation = operation;
        this.userRole = userRole;
        this.identifier = identifier;
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.sessionId = sessionId;
        this.longText = longText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (tenantId != null ? !tenantId.equals(event.tenantId) : event.tenantId != null) return false;
        if (userId != null ? !userId.equals(event.userId) : event.userId != null) return false;
        if (timestamp != null ? !timestamp.equals(event.timestamp) : event.timestamp != null) return false;
        if (operation != null ? !operation.equals(event.operation) : event.operation != null) return false;
        if (userRole != null ? !userRole.equals(event.userRole) : event.userRole != null) return false;
        if (identifier != null ? !identifier.equals(event.identifier) : event.identifier != null) return false;
        if (action != null ? !action.equals(event.action) : event.action != null) return false;
        if (entity != null ? !entity.equals(event.entity) : event.entity != null) return false;
        if (entityId != null ? !entityId.equals(event.entityId) : event.entityId != null) return false;
        if (sessionId != null ? !sessionId.equals(event.sessionId) : event.sessionId != null) return false;
        return longText != null ? longText.equals(event.longText) : event.longText == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (entityId != null ? entityId.hashCode() : 0);
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        result = 31 * result + (longText != null ? longText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", tenantId=" + tenantId +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                ", operation='" + operation + '\'' +
                ", userRole='" + userRole + '\'' +
                ", identifier=" + identifier +
                ", action='" + action + '\'' +
                ", entity='" + entity + '\'' +
                ", entityId=" + entityId +
                ", sessionId='" + sessionId + '\'' +
                ", longText='" + longText + '\'' +
                '}';
    }
}
