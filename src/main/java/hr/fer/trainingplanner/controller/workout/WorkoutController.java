package hr.fer.trainingplanner.controller.workout;

import hr.fer.trainingplanner.domain.AMRAP.AMRAP;
import hr.fer.trainingplanner.domain.EMOM.EMOM;
import hr.fer.trainingplanner.domain.fortime.ForTime;
import hr.fer.trainingplanner.domain.normal.Normal;
import hr.fer.trainingplanner.domain.tabata.Tabata;
import hr.fer.trainingplanner.domain.workout.Workout;
import hr.fer.trainingplanner.domain.workout.WorkoutResponse;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import hr.fer.trainingplanner.service.AMRAP.AMRAPService;
import hr.fer.trainingplanner.service.EMOM.EMOMService;
import hr.fer.trainingplanner.service.fortime.ForTimeService;
import hr.fer.trainingplanner.service.normal.NormalService;
import hr.fer.trainingplanner.service.tabata.TabataService;
import hr.fer.trainingplanner.service.workout.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkoutController {

    private final WorkoutService workoutService;

    private final AMRAPService amrapService;

    private final EMOMService emomService;

    private final ForTimeService forTimeService;

    private final NormalService normalService;

    private final TabataService tabataService;

    @Autowired
    public WorkoutController(
            final WorkoutService workoutService,
            final AMRAPService amrapService,
            final EMOMService emomService,
            final ForTimeService forTimeService,
            final NormalService normalService,
            final TabataService tabataService) {
        this.workoutService = workoutService;
        this.amrapService = amrapService;
        this.emomService = emomService;
        this.forTimeService = forTimeService;
        this.normalService = normalService;
        this.tabataService = tabataService;
    }

    @GetMapping("/api/workout")
    public List<WorkoutResponse> getAll() {
        List<Workout> workouts = this.workoutService.getAll();

        List<WorkoutResponse> workoutResponses = new ArrayList<>();
        for (Workout workout : workouts) {
            if (workout.getType().equals(WorkoutType.AMRAP)) {
                WorkoutResponse workoutResponse = this.amrapService.getResponse((AMRAP) workout);
                workoutResponses.add(workoutResponse);
            } else if (workout.getType().equals(WorkoutType.EMOM)) {
                WorkoutResponse workoutResponse = this.emomService.getResponse((EMOM) workout);
                workoutResponses.add(workoutResponse);
            } else if (workout.getType().equals(WorkoutType.FOR_TIME)) {
                WorkoutResponse workoutResponse = this.forTimeService.getResponse((ForTime) workout);
                workoutResponses.add(workoutResponse);
            } else if (workout.getType().equals(WorkoutType.NORMAL)) {
                WorkoutResponse workoutResponse = this.normalService.getResponse((Normal) workout);
                workoutResponses.add(workoutResponse);
            } else if (workout.getType().equals(WorkoutType.TABATA)) {
                WorkoutResponse workoutResponse = this.tabataService.getResponse((Tabata) workout);
                workoutResponses.add(workoutResponse);
            }
        }

        return workoutResponses;
    }
}
