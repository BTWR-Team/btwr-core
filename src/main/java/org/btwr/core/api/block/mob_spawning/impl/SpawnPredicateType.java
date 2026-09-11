package org.btwr.core.api.block.mob_spawning.impl;

import com.mojang.serialization.MapCodec;

public record SpawnPredicateType<P extends SpawnPredicate>(MapCodec<P> codec) {}