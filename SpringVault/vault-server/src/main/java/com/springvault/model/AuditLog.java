package com.springvault.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs", indexes = {
        @Index(name = "idx_audit_service", columnList = "service_name"),
        @Index(name = "idx_audit_timestamp", columnList = "timestamp"),
        @Index(name = "idx_audit_action", columnList = "action")
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "service_name", nullable = false)
    private String serviceName;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditAction action;

    /**
     * The secret path accessed (e.g., "database/prod/password")
     */
    @Column(name = "resource_path")
    private String resourcePath;

    /**
     * Environment of the requesting service
     */
    @Column
    private String environment;


    @Column
    private String region;

    /**
     * IP address of the caller
     */
    @Column(name = "ip_address")
    private String ipAddress;


    @Column(nullable = false)
    private Boolean success;


    @Column(name = "failure_reason")
    private String failureReason;


    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }

    public enum AuditAction {
        READ, WRITE, DELETE, ROTATE, AUTH, POLICY_CHECK, BATCH_READ, TOKEN_CREATED
    }
}
