package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConference is a Querydsl query type for Conference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConference extends EntityPathBase<Conference> {

    private static final long serialVersionUID = -625543586L;

    public static final QConference conference = new QConference("conference");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath callEndTime = createString("callEndTime");

    public final StringPath callStartTime = createString("callStartTime");

    public final NumberPath<Integer> conferenceCategory = createNumber("conferenceCategory", Integer.class);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> is_active = createNumber("is_active", Integer.class);

    public final NumberPath<Integer> ownerId = createNumber("ownerId", Integer.class);

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final StringPath title = createString("title");

    public QConference(String variable) {
        super(Conference.class, forVariable(variable));
    }

    public QConference(Path<? extends Conference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference(PathMetadata metadata) {
        super(Conference.class, metadata);
    }

}

