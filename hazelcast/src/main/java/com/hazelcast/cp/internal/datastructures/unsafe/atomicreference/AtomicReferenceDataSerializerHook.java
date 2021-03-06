/*
 * Copyright (c) 2008-2019, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.cp.internal.datastructures.unsafe.atomicreference;

import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.GetAndSetOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.MergeBackupOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.AlterAndGetOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.AlterOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.ApplyOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.AtomicReferenceReplicationOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.CompareAndSetOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.ContainsOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.GetAndAlterOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.GetOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.IsNullOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.MergeOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.SetBackupOperation;
import com.hazelcast.cp.internal.datastructures.unsafe.atomicreference.operations.SetOperation;
import com.hazelcast.internal.serialization.DataSerializerHook;
import com.hazelcast.internal.serialization.impl.FactoryIdHelper;
import com.hazelcast.nio.serialization.DataSerializableFactory;

import static com.hazelcast.internal.serialization.impl.FactoryIdHelper.ATOMIC_REFERENCE_DS_FACTORY;
import static com.hazelcast.internal.serialization.impl.FactoryIdHelper.ATOMIC_REFERENCE_DS_FACTORY_ID;

public final class AtomicReferenceDataSerializerHook implements DataSerializerHook {

    public static final int F_ID = FactoryIdHelper.getFactoryId(ATOMIC_REFERENCE_DS_FACTORY, ATOMIC_REFERENCE_DS_FACTORY_ID);

    public static final int ALTER_AND_GET = 0;
    public static final int ALTER = 1;
    public static final int APPLY = 2;
    public static final int COMPARE_AND_SET = 3;
    public static final int CONTAINS = 4;
    public static final int GET_AND_ALTER = 5;
    public static final int GET_AND_SET = 6;
    public static final int GET = 7;
    public static final int IS_NULL = 8;
    public static final int SET_BACKUP = 9;
    public static final int SET = 10;
    public static final int REPLICATION = 11;
    public static final int MERGE = 12;
    public static final int MERGE_BACKUP = 13;

    @Override
    public int getFactoryId() {
        return F_ID;
    }

    @Override
    public DataSerializableFactory createFactory() {
        return typeId -> {
            switch (typeId) {
                case ALTER_AND_GET:
                    return new AlterAndGetOperation();
                case ALTER:
                    return new AlterOperation();
                case APPLY:
                    return new ApplyOperation();
                case COMPARE_AND_SET:
                    return new CompareAndSetOperation();
                case CONTAINS:
                    return new ContainsOperation();
                case GET_AND_ALTER:
                    return new GetAndAlterOperation();
                case GET_AND_SET:
                    return new GetAndSetOperation();
                case GET:
                    return new GetOperation();
                case IS_NULL:
                    return new IsNullOperation();
                case SET_BACKUP:
                    return new SetBackupOperation();
                case SET:
                    return new SetOperation();
                case REPLICATION:
                    return new AtomicReferenceReplicationOperation();
                case MERGE:
                    return new MergeOperation();
                case MERGE_BACKUP:
                    return new MergeBackupOperation();
                default:
                    return null;
            }
        };
    }
}
