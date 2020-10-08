package com.karis.videoozone.util

interface EntityMapper<Entity, DormainModel> {

    fun mapFromEntity(entity: Entity): DormainModel

    fun mapToEntity(dormainModel: DormainModel): Entity



}