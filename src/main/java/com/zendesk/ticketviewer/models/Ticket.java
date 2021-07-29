package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@lombok.Data
//@JsonIgnoreProperties({"collaborators", "custom_fields", "email_cc_ids",
//"follower_ids", "followup_ids", "macro_ids", "sharing_agreement_ids", "tags"})
public class Ticket {
    private boolean allowAttachments;
    private boolean allowChannelBack;
    private long assigneeId;
    private long brandId;
    private long[] collaboratorIds;
    private Object[] collaborators;
    private String createdAt;
    private List<Pair<Long, String>> customFields;
    private String description;
    private String dueAt;
    private Object[] emailCCIds;
    private String externalId;
    private long[] followerIds;
    private long[] followupIds;
    private long forumTopicId;
    private long groupId;
    private boolean hasIncidents;
    private long id;
    private boolean isPublic;
    private long[] macroIds;
    private long organizationId;
    private String priority;
    private long problemId;
    private String rawSubject;
    private String recipient;
    private long requesterId;
    private SatisfactionRating satisfactionRating;
    private long[] sharingAgreementIds;
    private String status;
    private String subject;
    private long submitterId;
    private List<String> tags;
    private long ticketFormId;
    private String type;
    private String updatedAt;
    private String url;
    private Via via;
    private long viaFollowupSourceId;

    public Ticket() {}

    @JsonCreator
    public Ticket(@JsonProperty("allow_attachments") boolean allowAttachments,
                  @JsonProperty("allow_channelback") boolean allowChannelBack,
                  @JsonProperty("assignee_id") long assigneeId,
                  @JsonProperty("brand_id") long brandId,
                  @JsonProperty("collaborator_ids") long[] collaboratorIds,
                  @JsonProperty("collaborators") Object[] collaborators,
                  @JsonProperty("created_at") String createdAt,
                  @JsonProperty("custom_fields") List<Pair<Long, String>> customFields,
                  @JsonProperty("description") String description,
                  @JsonProperty("due_at") String dueAt,
                  @JsonProperty("email_cc_ids") String[] emailCCIds,
                  @JsonProperty("external_id") String externalId,
                  @JsonProperty("follower_ids") long[] followerIds,
                  @JsonProperty("followup_ids") long[] followupIds,
                  @JsonProperty("forum_topic_id") long forumTopicId,
                  @JsonProperty("group_id") long groupId,
                  @JsonProperty("has_incidents") boolean hasIncidents,
                  @JsonProperty("id") long id,
                  @JsonProperty("is_public") boolean isPublic,
                  @JsonProperty("macro_ids") long[] macroIds,
                  @JsonProperty("organization_id") long organizationId,
                  @JsonProperty("priority") String priority,
                  @JsonProperty("problem_id") long problemId,
                  @JsonProperty("raw_subject") String rawSubject,
                  @JsonProperty("recipient") String recipient,
                  @JsonProperty("requester_id") long requesterId,
                  @JsonProperty("satisfaction_rating") SatisfactionRating satisfactionRating,
                  @JsonProperty("sharing_agreement_ids") long[] sharingAgreementIds,
                  @JsonProperty("status") String status,
                  @JsonProperty("subject") String subject,
                  @JsonProperty("submitter_id") long submitterId,
                  @JsonProperty("tags") List<String> tags,
                  @JsonProperty("ticket_form_id") long ticketFormId,
                  @JsonProperty("type") String type,
                  @JsonProperty("updated_at") String updatedAt,
                  @JsonProperty("url") String url,
                  // For API - ticket endpoints this is not important
                  @JsonProperty("via") Via via,
                  @JsonProperty("via_followup_source_id") long viaFollowupSourceId
    ) {
        this.allowAttachments = allowAttachments;
        this.allowChannelBack = allowChannelBack;
        this.assigneeId = assigneeId;
        this.brandId = brandId;
        this.collaboratorIds = collaboratorIds;
        this.collaborators = collaborators;
        this.createdAt = createdAt;
        this.customFields = customFields;
        this.description = description;
        this.dueAt = dueAt;
        this.emailCCIds = emailCCIds;
        this.externalId = externalId;
        this.followerIds = followerIds;
        this.followupIds = followupIds;
        this.forumTopicId = forumTopicId;
        this.groupId = groupId;
        this.hasIncidents = hasIncidents;
        this.id = id;
        this.isPublic = isPublic;
        this.macroIds = macroIds;
        this.organizationId = organizationId;
        this.priority = priority;
        this.problemId = problemId;
        this.rawSubject = rawSubject;
        this.recipient = recipient;
        this.requesterId = requesterId;
        this.satisfactionRating = satisfactionRating;
        this.sharingAgreementIds = sharingAgreementIds;
        this.status = status;
        this.subject = subject;
        this.submitterId = submitterId;
        this.tags = tags;
        this.ticketFormId = ticketFormId;
        this.type = type;
        this.updatedAt = updatedAt;
        this.url = url;
        this.via = via;
        this.viaFollowupSourceId = viaFollowupSourceId;
    }
}
