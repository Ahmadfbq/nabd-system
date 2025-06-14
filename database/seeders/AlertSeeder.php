<?php

namespace Database\Seeders;

use App\Models\Alert;
use App\Models\User;
use App\Models\Measurement;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class AlertSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $users = User::all();

        if ($users->isEmpty()) {
            $this->command->info('No users found. Please run UserSeeder first.');
            return;
        }

        foreach ($users as $user) {
            // Get existing measurements for this user
            $measurements = Measurement::where('user_id', $user->id)->get();

            if ($measurements->isEmpty()) {
                $this->command->info("No measurements found for user {$user->id}. Skipping alerts.");
                continue;
            }

            foreach ($measurements->random(min(50, $measurements->count())) as $measurement) {
                if (fake()->boolean(20)) {
                    Alert::factory()->create([
                        'user_id' => $user->id,
                        'measurement_id' => $measurement->id,
                    ]);
                }
            }
        }
    }
}
