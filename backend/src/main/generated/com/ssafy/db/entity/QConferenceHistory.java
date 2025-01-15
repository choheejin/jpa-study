package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConferenceHistory is a Querydsl query type for ConferenceHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConferenceHistory extends EntityPathBase<ConferenceHistory> {

    private static final long serialVersionUID = -321961898L;

    public static final QConferenceHistory conferenceHistory = new QConferenceHistory("conferenceHistory");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> action = createNumber("action", Integer.class);

    public final NumberPath<Integer> conferenceId = createNumber("conferenceId", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath insertedTime = createString("insertedTime");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QConferenceHistory(String variable) {
        super(ConferenceHistory.class, forVariable(variable));
    }

    public QConferenceHistory(Path<? extends ConferenceHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConferenceHistory(PathMetadata metadata) {
        super(ConferenceHistory.class, metadata);
    }

}

