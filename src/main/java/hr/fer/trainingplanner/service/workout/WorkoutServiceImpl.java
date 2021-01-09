package hr.fer.trainingplanner.service.workout;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.workout.Workout;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import hr.fer.trainingplanner.repository.AMRAP.AMRAPRepository;
import hr.fer.trainingplanner.repository.EMOM.EMOMRepository;
import hr.fer.trainingplanner.repository.fortime.ForTimeRepository;
import hr.fer.trainingplanner.repository.normal.NormalRepository;
import hr.fer.trainingplanner.repository.setsandreps.SetsAndRepsRepository;
import hr.fer.trainingplanner.repository.tabata.TabataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private AMRAPRepository amrapRepository;

    private EMOMRepository emomRepository;

    private ForTimeRepository forTimeRepository;

    private NormalRepository normalRepository;

    private SetsAndRepsRepository setsAndRepsRepository;

    private TabataRepository tabataRepository;

    @Autowired
    public WorkoutServiceImpl(
            final AMRAPRepository amrapRepository,
            final EMOMRepository emomRepository,
            final ForTimeRepository forTimeRepository,
            final NormalRepository normalRepository,
            final SetsAndRepsRepository setsAndRepsRepository,
            final TabataRepository tabataRepository) {
        this.amrapRepository = amrapRepository;
        this.emomRepository = emomRepository;
        this.forTimeRepository = forTimeRepository;
        this.normalRepository = normalRepository;
        this.setsAndRepsRepository = setsAndRepsRepository;
        this.tabataRepository = tabataRepository;
    }

    @Override
    public List<Workout> getAll() {
        List<Workout> workouts = new ArrayList<>();

        workouts.addAll(Lists.newArrayList(this.amrapRepository.findAll()));
        workouts.addAll(Lists.newArrayList(this.emomRepository.findAll()));
        workouts.addAll(Lists.newArrayList(this.forTimeRepository.findAll()));
        workouts.addAll(Lists.newArrayList(this.normalRepository.findAll()));
        workouts.addAll(Lists.newArrayList(this.setsAndRepsRepository.findAll()));
        workouts.addAll(Lists.newArrayList(this.tabataRepository.findAll()));

        return workouts;
    }

    @Override
    public Workout getByWorkoutIdAndType(long workoutId, WorkoutType workoutType) {
        if (workoutType.equals(WorkoutType.AMRAP)) {
            return this.amrapRepository.findById(workoutId).orElse(null);
        } else if (workoutType.equals(WorkoutType.EMOM)) {
            return this.emomRepository.findById(workoutId).orElse(null);
        } else if (workoutType.equals(WorkoutType.FOR_TIME)) {
            return this.forTimeRepository.findById(workoutId).orElse(null);
        } else if (workoutType.equals(WorkoutType.NORMAL)) {
            return this.normalRepository.findById(workoutId).orElse(null);
        } else if (workoutType.equals(WorkoutType.SETS_AND_REPS)) {
            return this.setsAndRepsRepository.findById(workoutId).orElse(null);
        } else {
            return this.tabataRepository.findById(workoutId).orElse(null);
        }
    }
}
