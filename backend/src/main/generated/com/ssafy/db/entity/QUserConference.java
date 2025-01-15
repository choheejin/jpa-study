package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserConference is a Querydsl query type for UserConference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserConference extends EntityPathBase<UserConference> {

    private static final long serialVersionUID = 1175538569L;

    public static final QUserConference userConference = new QUserConference("userConference");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> conferenceId = createNumber("conferenceId", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUserConference(String variable) {
        super(UserConference.class, forVariable(variable));
    }

    public QUserConference(Path<? extends UserConference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserConference(PathMetadata metadata) {
        super(UserConference.class, metadata);
    }

}

